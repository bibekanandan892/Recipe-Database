package com.bibek.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<NetworkResult<T>>.collectResponse(
    scope: CoroutineScope,
    onSuccess: suspend (T?) -> Unit = {},
    onError: suspend (String) -> Unit = {},
    onLoading: suspend (Boolean) -> Unit = {}
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