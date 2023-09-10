package com.example.task_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task_1.ui.theme.TASK_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TASK_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Struct()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Struct() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Reminders",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta)
            )
        },
        content = { padding -> Reminders(Modifier.padding(padding)) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reminders(modifier: Modifier) {
    var texto by remember { mutableStateOf("") }
    var recordatorios by remember { mutableStateOf(listOf<String>()) }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 65.dp, start = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Row (modifier = Modifier.padding(10.dp)) {
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Give a reminder")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Magenta,
                    unfocusedSupportingTextColor = Color.Magenta),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)){
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = {
                    //ESTO SOLO AÑADE ELEMENTOS A LA LISTA (POR ALGUNA RAZON)
                    if (texto != "") {
                        recordatorios = recordatorios + texto
                        texto = ""
                    }
                }) {
                  Text(text = "Set reminder")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = true
        ){
            items(recordatorios){currentRec ->
                Text(
                    text = currentRec,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
