package com.gondroid.randomchallengeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.gondroid.randomchallengeapp.presentation.navigation.NavigationRoot
import com.gondroid.randomchallengeapp.ui.theme.RandomChallengeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomChallengeAppTheme {
                val navController = rememberNavController()
                NavigationRoot(navController)
            }
        }
    }
}

