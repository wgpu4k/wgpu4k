/**
 * return false if SNAPSHOT is set to "false" else true
 */
fun isSnapshot(): Boolean =
    System.getenv("SNAPSHOT")?.equals("false")?.not() ?: true