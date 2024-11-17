package com.gondroid.randomchallengeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.gondroid.randomchallengeapp.data.FakeTaskLocalDataSource
import com.gondroid.randomchallengeapp.domain.Task
import com.gondroid.randomchallengeapp.ui.theme.RandomChallengeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomChallengeAppTheme {

                var text by remember { mutableStateOf("") }
                val fakeList = FakeTaskLocalDataSource
                LaunchedEffect(true) {
                    fakeList.tasksFlow.collect {
                        text = it.toString()
                    }
                }

                LaunchedEffect(true) {
                    fakeList.addTask(
                        Task(
                            id = "1",
                            title = "Task 1",
                            description = "Description 1"
                        )
                    )
                }


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = text,
                        modifier = Modifier.padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun HelloWorld(
    modifier: Modifier = Modifier
) {

}