package com.bibek.recipedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bibek.core.utils.Destination
import com.bibek.core.utils.Navigator
import com.bibek.core.utils.Toaster
import com.bibek.recipedatabase.navigation.SetupNavGraph
import com.bibek.recipedatabase.ui.theme.RecipeDatabaseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var toaster: Toaster


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navGraphController = rememberNavController()
            LaunchedEffect(Unit) {
                withContext(Dispatchers.Main.immediate) {
                    navigator.actions.collectLatest { action ->
                        when (action) {
                            Navigator.Action.Back -> {
                                navGraphController.popBackStack()
                            }

                            is Navigator.Action.Navigate -> {
                                navGraphController.navigate(
                                    route = action.destination, builder = action.navOptions
                                )

                            }
                        }
                    }
                }
            }
            LaunchedEffect(key1 = true) {
                withContext(Dispatchers.Main.immediate) {
                    toaster.errorFlow.collect {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            LaunchedEffect(key1 = true) {
                withContext(Dispatchers.Main.immediate) {
                    toaster.successFlow.collectLatest {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            RecipeDatabaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        SetupNavGraph(
                            startDestination = Destination.Home.route,
                            navController = navGraphController,

                            )
                    }

                }
            }
        }
    }
}
