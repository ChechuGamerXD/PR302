package com.jesusaledo.pr302

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jesusaledo.pr302.ui.theme.PR302Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PR302Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Banco()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Banco() {
    var saldo by rememberSaveable {
        mutableStateOf(0.0)
    }
    var inOutMoney by rememberSaveable {
        mutableStateOf("")
    }
    var secure by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Banco Bebe un Vaso de Agua\nTu saldo es de $saldo€")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inOutMoney,
            onValueChange = {inOutMoney = it; secure = false },
            label = { Text("Introduce el dinero") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                secure = inOutMoney.isNotBlank() && inOutMoney.toDoubleOrNull() != null && inOutMoney.toDouble() >= 0
                if (secure)
                    saldo += inOutMoney.toDouble()
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(bottom = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ingresar dinero")
        }
        Button(
            onClick = {
                secure = false
                secure = inOutMoney.isNotBlank() && inOutMoney.toDoubleOrNull() != null && inOutMoney.toDouble() >= 0 && inOutMoney.toDouble() <= saldo
                if (secure)
                    saldo -= inOutMoney.toDouble()
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(bottom = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Retirar dinero")
        }
    }
}
