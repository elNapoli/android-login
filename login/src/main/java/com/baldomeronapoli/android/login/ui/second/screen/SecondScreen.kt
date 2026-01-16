package com.baldomeronapoli.android.login.ui.second.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SecondScreen(onClick: () -> Unit) {
    Column {

        Button(onClick = onClick) { Text("jojojo") }
    }

}