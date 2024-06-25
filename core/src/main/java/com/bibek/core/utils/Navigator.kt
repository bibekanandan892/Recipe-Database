package com.bibek.core.utils

import androidx.compose.runtime.Stable
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
/**
 * Author: Bibekananda Nayak
 * Date: 21-02-2024
Description: This Kotlin class represents a Navigator, which is annotated with @Singleton and @Stable.
It provides functionality for navigation within an application, utilizing a MutableSharedFlow to emit navigation actions.
It supports navigation to a specified destination with optional navigation options, as well as a back navigation action.
*/

@Stable
@Singleton
class Navigator @Inject constructor() {

    // Private mutable shared flow to emit navigation actions
    private val _actions = MutableSharedFlow<Action>(
        replay = 0,
        extraBufferCapacity = 10
    )

    // Public immutable flow of navigation actions
    val actions: Flow<Action> = _actions

    // Function to navigate to a specified destination with optional navigation options
    fun navigate(destination: DestinationRoute, navOptions: NavOptionsBuilder.() -> Unit = {}) {
        _actions.tryEmit(
            Action.Navigate(destination = destination, navOptions = navOptions)
        )
    }

    // Function to perform a back navigation action
    fun back() {
        _actions.tryEmit(Action.Back)
    }

    // Sealed class representing different types of navigation actions
    sealed class Action {
        data class Navigate(
            val destination: DestinationRoute,
            val navOptions: NavOptionsBuilder.() -> Unit
        ) : Action()

        object Back : Action()
    }
}
typealias DestinationRoute = String // Alias for DestinationRoute for better readability
