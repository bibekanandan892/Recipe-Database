package com.bibek.recipedatabase.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.navigation.Destination
import com.bibek.core.utils.navigation.Navigator
import com.bibek.recipedatabase.navigation.SetupNavGraph
import com.bibek.recipedatabase.presentation.componets.ConnectivityStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toasterSetup()

        setContent {
            val navGraphController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()
            val isConnectivityAvailable by mainViewModel.isConnectivityAvailable

            LaunchedEffect(key1 = true) {
                navigationSetup(navGraphController)
            }

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    isConnectivityAvailable?.let {
                        ConnectivityStatus(it)
                    }
                    SetupNavGraph(
                        startDestination = Destination.HOME.name,
                        navController = navGraphController
                    )
                }
            }
        }
    }

    private fun navigationSetup(navGraphController: NavHostController) {
        navigator.actions.onEach { action ->
            when (action) {
                Navigator.Action.Back -> {
                    navGraphController.popBackStack()
                }
                is Navigator.Action.Navigate -> {
                    if (navGraphController.currentDestination?.route != action.destination) {
                        navGraphController.navigate(
                            route = action.destination, builder = action.navOptions
                        )
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun toasterSetup() {
        toaster.errorFlow.onEach {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
        toaster.successFlow.onEach {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }
}

