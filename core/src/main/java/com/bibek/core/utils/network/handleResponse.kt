package com.bibek.core.utils.network

import com.bibek.core.utils.CONNECTION_TIMEOUT_MESSAGE
import com.bibek.core.utils.ERROR_DESCRIPTION_KEY
import com.bibek.core.utils.GENERIC_ERROR_MESSAGE
import com.bibek.core.utils.MESSAGE_KEY
import com.bibek.core.utils.SOCKET_TIMEOUT_MESSAGE
import com.bibek.core.utils.STATUS_DESC_KEY
import com.bibek.core.utils.UNKNOWN_ERROR_MESSAGE
import com.bibek.core.utils.UNKNOWN_IO_ERROR_MESSAGE
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
            emit(NetworkResult.Error(CONNECTION_TIMEOUT_MESSAGE))
            e.printStackTrace()
        } catch (e: SocketTimeoutException) {
            emit(NetworkResult.Error(SOCKET_TIMEOUT_MESSAGE))
            e.printStackTrace()
        } catch (e: IOException) {
            emit(NetworkResult.Error(e.message ?: UNKNOWN_IO_ERROR_MESSAGE))
            e.printStackTrace()
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: GENERIC_ERROR_MESSAGE))
            e.printStackTrace()
        }
    }
}

fun getErrorDes(
    errorKeys: List<String> = listOf(ERROR_DESCRIPTION_KEY, MESSAGE_KEY, STATUS_DESC_KEY),
    errorString: String
): String? {
    try {
        val errorObj = JSONObject(errorString)
        errorKeys.forEach { errorKey ->
            if (errorObj.has(errorKey)) {
                return errorObj.getString(errorKey).toString()
            }
        }
        return UNKNOWN_ERROR_MESSAGE
    } catch (e: Exception) {
        return e.message
    }
}
