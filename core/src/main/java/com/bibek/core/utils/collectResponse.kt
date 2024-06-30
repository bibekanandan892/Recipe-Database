package com.bibek.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<NetworkResult<T>>.collectResponse(
    scope: CoroutineScope,
    crossinline onSuccess: suspend (T?) -> Unit = {},
    crossinline onError: suspend (String) -> Unit = {},
    crossinline onLoading: suspend (Boolean) -> Unit = {}
) {
    scope.launch {
        collect{networkResult->
            onLoading(false)
            when(networkResult){
                is NetworkResult.Error -> onError(networkResult.message?:"Unknown Error")
                is NetworkResult.Loading -> onLoading(true)
                is NetworkResult.Success ->  { onSuccess(networkResult.data) }
            }
        }
    }
}

suspend inline fun <T> Flow<NetworkResult<T>>.collectResponse(
     crossinline onSuccess: suspend (T?) -> Unit ,
     crossinline onError: suspend (String) -> Unit,
     crossinline onLoading: suspend (Boolean) -> Unit
)  {
        collect{networkResult->
            onLoading(false)
            when(networkResult){
                is NetworkResult.Error -> onError(networkResult.message?:"Unknown Error")
                is NetworkResult.Loading -> onLoading(true)
                is NetworkResult.Success ->  { onSuccess(networkResult.data) }
            }
        }

}