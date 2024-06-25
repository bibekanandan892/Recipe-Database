package com.bibek.core.utils

/**
 * This function checks if a nullable string is null or not.
 * If the string is null, it returns "NA".
 * If the string is not null, it returns the original string.
 *
 * @return The original string if not null, otherwise "NA".
 */
fun String?.checkNull(): String {
    if (this == null) {
        return "NA"
    }
    return this
}