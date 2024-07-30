/*
 * Copyright 2007, 2008, 2009 Duncan McGregor
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

package org.rococoa.internal;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import org.rococoa.RococoaException;
import org.rococoa.cocoa.foundation.NSInvocation;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NSInvocationStructureMapper extends NSInvocationMapper {

    @SuppressWarnings("unchecked")
    public NSInvocationStructureMapper(Class<?> type) {
        super(encodeStruct((Class<? extends Structure>) type), type);
    }

    private static String encodeStruct(Class<? extends Structure> clas) {
        StringBuilder result = new StringBuilder();
        if (!(Structure.ByValue.class.isAssignableFrom(clas))) {
            result.append('^'); // pointer to
        }
        result.append('{').append(clas.getSimpleName()).append('=');
        for (Field f : collectStructFields(clas, new ArrayList<>())) {
            result.append(NSInvocationMapperLookup.stringForType(f.getType()));
        }
        return result.append('}').toString();
    }

    @SuppressWarnings("unchecked")
    private static List<Field> collectStructFields(Class<? extends Structure> clas, List<Field> list) {
        if (clas == Structure.class) {
            return list;
        }
        Collections.addAll(list, clas.getDeclaredFields());
        return collectStructFields((Class<? extends Structure>) clas.getSuperclass(), list);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object readArgumentFrom(NSInvocation invocation, int index, Class<?> type) {
        if (Structure.ByValue.class.isAssignableFrom(type)) {
            return readStructureByValue(invocation, index, (Class<? extends Structure>) type);
        } else {
            return readStructureByReference(invocation, index, (Class<? extends Structure>) type);
        }
    }

    @Override
    public Memory bufferForResult(Object methodCallResult) {
        if (methodCallResult instanceof Structure.ByValue) {
            return bufferForStructureByValue((Structure) methodCallResult);
        } else {
            return bufferForStructureByReference((Structure) methodCallResult);
        }
    }

    private Structure readStructureByValue(NSInvocation invocation, int index,
                                           Class<? extends Structure> type) {
        Structure result = newInstance(type);
        Memory buffer = new Memory(result.size());
        invocation.getArgument_atIndex(buffer, index);
        return copyBufferToStructure(buffer, result);
    }

    private Structure readStructureByReference(NSInvocation invocation, int index,
                                               Class<? extends Structure> type) {
        Memory buffer = new Memory(Native.POINTER_SIZE);
        invocation.getArgument_atIndex(buffer, index);
        Pointer pointerToResult = buffer.getPointer(0);
        Structure result = newInstance(type);
        return copyBufferToStructure(pointerToResult, result);
    }

    @SuppressWarnings("unchecked")
    private <T> T newInstance(Class<?> clas) {
        try {
            return (T) clas.newInstance();
        } catch (Exception e) {
            throw new RococoaException("Could not instantiate " + clas, e);
        }
    }

    private Structure copyBufferToStructure(Pointer buffer, Structure structure) {
        int byteCount = structure.size();
        memcpy(structure.getPointer(), buffer, byteCount);
        structure.read();
        return structure;
    }

    private Memory bufferForStructureByValue(Structure methodCallResult) {
        methodCallResult.write();
        int byteCount = methodCallResult.size();
        Memory buffer = new Memory(byteCount);
        memcpy(buffer, methodCallResult.getPointer(), byteCount);
        return buffer;
    }

    private Memory bufferForStructureByReference(Structure methodCallResult) {
        methodCallResult.write();
        Memory buffer = new Memory(Native.POINTER_SIZE);
        buffer.setPointer(0, methodCallResult.getPointer());
        return buffer;
    }

    private void memcpy(Pointer dest, Pointer src, int byteCount) {
        memcpyViaByteBuffer(dest, src, byteCount);
    }

    @SuppressWarnings("unused") // kept as naive implementation
    private void memcpyViaArray(Pointer dest, Pointer src, int byteCount) {
        byte[] structBytes = new byte[byteCount];
        src.read(0, structBytes, 0, byteCount);
        dest.write(0, structBytes, 0, byteCount);
    }

    private void memcpyViaByteBuffer(Pointer dest, Pointer src, int byteCount) {
        ByteBuffer destBuffer = dest.getByteBuffer(0, byteCount);
        ByteBuffer srcBuffer = src.getByteBuffer(0, byteCount);
        destBuffer.put(srcBuffer);
    }
}