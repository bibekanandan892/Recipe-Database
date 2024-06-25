package com.bibek.core.utils

import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

/**
Author: Bibekananda Nayak
Date: 2024-02-21
Description: This function handles network responses asynchronously using Kotlin Flow.
It accepts a suspending lambda function 'call' that makes the network request and returns an HTTP response.
The function emits a sequence of NetworkResult<T> objects representing the loading state,
success state with the response body of type T,
or various error states depending on the encountered exceptions during the network call.

The 'handleResponse' function emits a loading state, then invokes the provided 'call' lambda to make the network request.
It catches various exceptions that may occur during the network call, such as ClientRequestException, ServerResponseException,
RedirectResponseException, ResponseException, ConnectTimeoutException, SocketTimeoutException, IOException, and other general exceptions.
Depending on the type of exception caught, it extracts error information from the response body (if available) or uses default error messages.
It then emits a corresponding NetworkResult.Error object with the error message.

The 'getErrorDes' function is a utility function to extract error descriptions from the JSON response body. It takes a list of error keys and the error string, attempts to parse it as JSON, and iterates through the error keys to find the first matching key in the JSON object. If found, it returns the corresponding error description; otherwise, it returns a default "Unknown Error" message.

 */

inline fun <reified T> handleResponse(crossinline call: suspend () -> HttpResponse): Flow<NetworkResult<T>> {
    return flow {
        emit(NetworkResult.Loading())
        try {
            val response = call.invoke().body<T>()
            emit(NetworkResult.Success(response))
        } catch (e: ClientRequestException) {
            val errorMessage = getErrorDes(errorString = e.response.body())
            if (errorMessage != null) {
                emit(NetworkResult.Error(errorMessage))
            } else {
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: ServerResponseException) {

            val errorMessage = getErrorDes(errorString = e.response.body())
            if (errorMessage != null) {
                emit(NetworkResult.Error(errorMessage))
            } else {
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: RedirectResponseException) {

            val errorMessage = getErrorDes(errorString = e.response.body())
            if (errorMessage != null) {
                emit(NetworkResult.Error(errorMessage))
            } else {
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: ResponseException) {
            val errorMessage = getErrorDes(errorString = e.response.body())
            if (errorMessage != null) {
                emit(NetworkResult.Error(errorMessage))
            } else {
                emit(NetworkResult.Error(e.response.status.description))
            }
        } catch (e: ConnectTimeoutException) {

            emit(NetworkResult.Error("Connection Timeout"))
        } catch (e: SocketTimeoutException) {
            emit(NetworkResult.Error("Socket Timeout"))
        } catch (e: IOException) {

            emit(NetworkResult.Error(e.message ?: "Unknown IO Error"))
        } catch (e: Exception) {

            emit(NetworkResult.Error(e.message ?: "Unknown Error"))
        }
    }
}



fun getErrorDes(
    errorKeys: List<String> = listOf("error_description", "message", "statusDesc"),
    errorString: String
): String? {
    try {
        val errorObj = JSONObject(errorString)
        errorKeys.forEach { errorKey ->
            if (errorObj.has(errorKey)) {
                return errorObj.getString(errorKey).toString()
            }
        }
        return "Unknown Error"
    } catch (e: Exception) {
        return e.message
    }
}