package com.bibek.core.utils.navigation

import androidx.compose.runtime.Stable
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Stable
@Singleton
class Navigator @Inject constructor() {
    private val _actions = MutableSharedFlow<Action>(
        replay = 0,
        extraBufferCapacity = 10
    )
    val actions: Flow<Action> = _actions
    fun navigate(destination: DestinationRoute, navOptions: NavOptionsBuilder.() -> Unit = {}) {
        _actions.tryEmit(
            Action.Navigate(destination = destination, navOptions = navOptions)
        )
    }
    fun back() {
        _actions.tryEmit(Action.Back)
    }
    sealed class Action {
        data class Navigate(
            val destination: DestinationRoute,
            val navOptions: NavOptionsBuilder.() -> Unit
        ) : Action()
        data object Back : Action()
    }
}
typealias DestinationRoute = String