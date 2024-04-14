package ca.markstrendin.markspasswordgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.markstrendin.markspasswordgenerator.ui.theme.MarksPasswordGeneratorTheme

// Docs on how UI element bits work:
// https://developer.android.com/develop/ui/compose/tutorial

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarksPasswordGeneratorTheme {
                Column(
                    Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    PromptInput(label = "Prompt", defaultValue = "")
                    Spacer(modifier = Modifier.height(20.dp))
                    OutputSection("With Special Characters")

                    Spacer(modifier = Modifier.height(20.dp))
                    OutputSection("Alpha Only")
                }

            }
        }
    }
}

@Composable
fun PromptInput(label: String, defaultValue: String) {
    var inputTextValue by remember { mutableStateOf(defaultValue) }

    TextField(
        value = inputTextValue,
        onValueChange = { inputTextValue = it },
        singleLine = true,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = false,
            capitalization = KeyboardCapitalization.None,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
    )
}

@Composable
fun OutputSection(label: String) {
    var inputTextValue by remember { mutableStateOf("asdfasf") }
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Title(label)
        TextField(
            value = inputTextValue,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrect = false,
                capitalization = KeyboardCapitalization.None,
            ),
            onValueChange = { },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
        )
        FilledTonalButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) { Text("Copy first 8")}
        FilledTonalButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {Text("Copy first 12")}
        FilledTonalButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {Text("Copy first 15")}
        FilledTonalButton(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {Text("Copy first 20")}
    }
}

@Composable
fun Title(name: String) {
    Text(
            text = name,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
    )
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarksPasswordGeneratorTheme {
        Greeting("Android")
    }
}

 */