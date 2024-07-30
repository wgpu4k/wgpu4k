/*
 * Copyright 2007, 2008 Duncan McGregor
 *
 * This file is part of Rococoa, a library to allow Java to talk to Cocoa.
 *
 * Rococoa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Rococoa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Rococoa.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.rococoa;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.rococoa.cocoa.CFIndex;
import org.rococoa.internal.*;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The core of Rococoa - statics to handle selectors and messaging at a function call level.
 * <p>
 * Marshalling of Java types to C types is handled by JNA. Marshalling of Java
 * type to Objective-C types is handled by RococoaTypeMapper.
 * <p>
 * Not to be confused with the Mac Foundation or Core Foundation frameworks, most
 * users shouldn't need to access this class directly.
 *
 * @author duncan
 */
public abstract class Foundation {

    private static final Logger logging = Logger.getLogger("org.rococoa.foundation");

    private static final FoundationLibrary foundationLibrary;
    private static final MsgSendLibrary messageSendLibrary;
    private static final RococoaLibrary rococoaLibrary;

    private static final Map<String, Selector> selectorCache = new HashMap<>();

    static {
        logging.finest("Initializing Foundation");

        // Set JNA to convert java.lang.String to char* using UTF-8, and match that with
        // the way we tell CF to interpret our char*
        // May be removed if we use toStringViaUTF16
        System.setProperty("jna.encoding", "UTF8");

        Map<String, Object> messageSendLibraryOptions = new HashMap<>(1);
        messageSendLibraryOptions.put(Library.OPTION_INVOCATION_MAPPER, new MsgSendInvocationMapper());
        messageSendLibrary = Native.load("Foundation", MsgSendLibrary.class, messageSendLibraryOptions);

        foundationLibrary = Native.load("Foundation", FoundationLibrary.class);
        rococoaLibrary = Native.load("rococoa", RococoaLibrary.class);
        logging.finest("exit initializing Foundation");
    }

    private Foundation() {
        //
    }

    public static void nsLog(String format, Object thing) {
        ID formatAsCFString = cfString(format);
        try {
            foundationLibrary.NSLog(formatAsCFString, thing);
        } finally {
            cfRelease(formatAsCFString);
        }
    }

    /**
     * Return a CFString as an ID, toll-free bridged to NSString.
     * <p>
     * Note that the returned string must be freed with {@link #cfRelease(ID)}.
     */
    public static ID cfString(String s) {
        // Use a byte[] rather than letting jna do the String -> char* marshalling itself.
        // Turns out about 10% quicker for long strings.
        byte[] utf16Bytes = s.getBytes(StandardCharsets.UTF_16LE);
        return foundationLibrary.CFStringCreateWithBytes(null, utf16Bytes,
                utf16Bytes.length,
                StringEncoding.kCFStringEncodingUTF16LE.value, (byte) 0);
    }

    /**
     * Retain the NSObject with id
     */
    public static ID cfRetain(ID id) {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("calling cfRetain(%s)", id));
        }
        return foundationLibrary.CFRetain(id);
    }

    /**
     * Release the NSObject with id
     */
    public static void cfRelease(ID id) {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("calling cfRelease(%s)", id));
        }
        foundationLibrary.CFRelease(id);
    }

    public static CFIndex cfGetRetainCount(ID cfTypeRef) {
        return foundationLibrary.CFGetRetainCount(cfTypeRef);
    }

    public static String toString(ID cfString) {
        return toStringViaUTF8(cfString);
    }

    /* Experimental */
    static String toStringViaUTF16(ID cfString) {
        int lengthInChars = foundationLibrary.CFStringGetLength(cfString);
        int potentialLengthInBytes = 3 * lengthInChars + 1; // UTF16 fully escaped 16 bit chars, plus nul

        byte[] buffer = new byte[potentialLengthInBytes];
        byte ok = foundationLibrary.CFStringGetCString(cfString, buffer, buffer.length, StringEncoding.kCFStringEncodingUTF16LE.value);
        if (ok == 0) {
            throw new RococoaException("Could not convert string");
        }
        return new String(buffer, StandardCharsets.UTF_16LE).substring(0, lengthInChars);
    }

    static String toStringViaUTF8(ID cfString) {
        int lengthInChars = foundationLibrary.CFStringGetLength(cfString);
        int potentialLengthInBytes = 3 * lengthInChars + 1; // UTF8 fully escaped 16 bit chars, plus nul

        byte[] buffer = new byte[potentialLengthInBytes];
        byte ok = foundationLibrary.CFStringGetCString(cfString, buffer, buffer.length, StringEncoding.kCFStringEncodingUTF8.value);
        if (ok == 0) {
            throw new RococoaException("Could not convert string");
        }
        return Native.toString(buffer);
    }

    /**
     * Get the ID of the NSClass with className
     */
    public static ID getClass(String className) {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("calling objc_getClass(%s)", className));
        }
        return foundationLibrary.objc_getClass(className);
    }

    public static Selector selector(String selectorName) {
        Selector cached = selectorCache.get(selectorName);
        if (cached != null) {
            return cached;
        }
        Selector result = foundationLibrary.sel_registerName(selectorName).initName(selectorName);
        selectorCache.put(selectorName, result);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T send(ID receiver, String selectorName, Class<T> returnType, Object... args) {
        return send(receiver, selectorName, returnType, null, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T send(ID receiver, String selectorName, Class<T> returnType, Method method, Object... args) {
        return send(receiver, selector(selectorName), returnType, method, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T send(ID receiver, Selector selector, Class<T> returnType, Object... args) {
        return send(receiver, selector, returnType, null, args);
    }

    /**
     * Send message with selector to receiver, passing args, expecting returnType.
     * <p>
     * Note that you are responsible for memory management if returnType is ID.
     *
     * @param returnType Expected return type mapping
     * @param method     Used to determine if variadic function call is required
     * @param args       Arguments including ID and selector
     */
    @SuppressWarnings("unchecked")
    public static <T> T send(ID receiver, Selector selector, Class<T> returnType, Method method, Object... args) {
        if (logging.isLoggable(Level.FINEST)) {
            logging.finest(String.format("sending (%s) %s.%s(%s)",
                    returnType.getSimpleName(), receiver, selector.getName(), new VarArgsUnpacker(args)));
        }
        if (method != null && method.isVarArgs()) {
            return (T) messageSendLibrary.syntheticSendVarArgsMessage(returnType, receiver, selector, args);
        }
        return (T) messageSendLibrary.syntheticSendMessage(returnType, receiver, selector, args);
    }

    /**
     * Convenience as this happens a lot in tests.
     * <p>
     * Note that you are responsible for memory management for the returned ID
     */
    public static ID sendReturnsID(ID receiver, String selectorName, Object... args) {
        return send(receiver, selector(selectorName), ID.class, args);
    }

    /**
     * Convenience as this happens a lot in tests.
     */
    public static void sendReturnsVoid(ID receiver, String selectorName, Object... args) {
        send(receiver, selector(selectorName), void.class, args);
    }

    public static boolean isMainThread() {
        return MainThreadUtils.isMainThread();
    }

    /**
     * Return the result of calling callable on the main Cococoa thread.
     */
    public static <T> T callOnMainThread(final Callable<T> callable) {
        return MainThreadUtils.callOnMainThread(rococoaLibrary, callable);
    }

    /**
     * Run runnable on the main Cococoa thread, waiting for completion.
     */
    public static void runOnMainThread(final Runnable runnable) {
        MainThreadUtils.runOnMainThread(rococoaLibrary, runnable, true);
    }

    /**
     * Run runnable on the main Cococoa thread, optionally waiting for completion.
     */
    public static void runOnMainThread(Runnable runnable, boolean waitUntilDone) {
        MainThreadUtils.runOnMainThread(rococoaLibrary, runnable, waitUntilDone);
    }

    /**
     * Create an Objective-C object which delegates to callbacks when methods
     * are invoked on it.
     * <p>
     * Object is created with alloc, so is owned by the caller.
     */
    public static ID newOCProxy(OCInvocationCallbacks callbacks) {
        return rococoaLibrary.proxyForJavaObject(
                callbacks.selectorInvokedCallback,
                callbacks.methodSignatureCallback);
    }

    public static boolean selectorNameMeansWeOwnReturnedObject(String selectorName) {
        // From Memory Management Programming Guide for Cocoa
        // This is the fundamental rule:
        // You take ownership of an object if you create it using a method whose
        // name begins with 'alloc' or 'new' or contains 'copy' (for example,
        // alloc, newObject, or mutableCopy), or if you send it a retain
        // message. You are responsible for relinquishing ownership of objects
        // you own using release or autorelease. Any other time you receive an
        // object, you must not release it.

        // Note that this does not appear to be an infallible rule - see
        // https://rococoa.dev.java.net/servlets/ReadMsg?list=dev&msgNo=71
        return selectorName.startsWith("alloc") ||
                selectorName.startsWith("new") ||
                selectorName.toLowerCase().contains("copy");
    }

}
