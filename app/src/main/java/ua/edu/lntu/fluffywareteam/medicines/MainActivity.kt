package ua.edu.lntu.fluffywareteam.medicines

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edu.lntu.fluffywareteam.medicines.ui.theme.FluffywareTeamMedicinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FluffywareTeamMedicinesTheme {
                MedicinesApp()
            }
        }
    }
}

@Composable
fun MedicinesApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Text
            Text(
                text = "Всі ліки",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add Button
            AddButton()
        }
    }
}

@Composable
fun AddButton() {
    val context = LocalContext.current
    val errorMessage = "Не вдається додати новий лікарський засіб  =("

    Button(
        onClick = { Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show() },
        colors = ButtonColors(
            containerColor = Color(0xFFB39DDB),
            contentColor = Color(0xFFB39DDB),
            disabledContainerColor = Color(0xFFB39DDB),
            disabledContentColor = Color(0xFFB39DDB),
        ),
         modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFB39DDB), RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        Text(
            text = "Додати",
            fontSize = 24.sp,
            color = Color.Black
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black
        )
    }
}

@Composable
fun BottomNavigationBar() {
    val context = LocalContext.current;
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {
            jumpToActivity<MainActivity>(context)
        }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = { jumpToActivity<UniversalActivity>(context) }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = { jumpToActivity<UniversalActivity>(context) }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = {
            jumpToActivity<AboutActivity>(context)
        }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(0xFFB39DDB)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicinesAppPreview() {
    FluffywareTeamMedicinesTheme {
        MedicinesApp()
    }
}
