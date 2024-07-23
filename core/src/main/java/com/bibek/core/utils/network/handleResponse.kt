package com.bibek.core.utils.network

import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.io.IOException

inline fun <reified T> handleResponse(crossinline call: suspend () -> HttpResponse): Flow<NetworkResult<T>> {
    return flow {
        emit(NetworkResult.Loading())
        try {
            val response = call.invoke().body<T>()
            emit(NetworkResult.Success(response))
        } catch (e: ResponseException) {
            val errorMessage = getErrorDes(errorString = e.response.body())
            emit(NetworkResult.Error(errorMessage ?: e.response.status.description))
        } catch (e: ConnectTimeoutException) {
            emit(NetworkResult.Error("Connection Timeout"))
            e.printStackTrace()
        } catch (e: SocketTimeoutException) {
            emit(NetworkResult.Error("Socket Timeout"))
            e.printStackTrace()
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.message ?: "Unknown IO Error"))
            e.printStackTrace()
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Something went wrong"))
            e.printStackTrace()
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