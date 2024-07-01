package com.bibek.core.utils.network

/**
Author: Bibekananda Nayak
Date: 21-02-2024

Description:
This Kotlin code defines a sealed class called NetworkResult, which represents the result of a network operation.
It is a generic class, allowing the result to hold data of any type. There are three subclasses of NetworkResult:

1. Success<T>: Represents a successful network operation, containing the retrieved data of type T.
2. Error<T>: Represents a failed network operation, containing an error message and optional data of type T.
3. Loading<T>: Represents an ongoing network operation, where the result is still pending.

This sealed class provides a structured way to handle different states and outcomes of network requests within Kotlin applications.
*/

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    // Represents a successful network operation with the retrieved data
    class Success<T>(data: T?) : NetworkResult<T>(data)

    // Represents a failed network operation with an error message and optional data
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)

    // Represents an ongoing network operation
    class Loading<T> : NetworkResult<T>()
}
