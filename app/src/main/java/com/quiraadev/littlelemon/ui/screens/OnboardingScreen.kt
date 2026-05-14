package com.quiraadev.littlelemon.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.ui.theme.PrimaryGreen
import com.quiraadev.littlelemon.ui.theme.PrimaryYellow
import androidx.core.content.edit
import com.quiraadev.littlelemon.R
import com.quiraadev.littlelemon.ui.routes.Home
import com.quiraadev.littlelemon.ui.routes.Onboarding

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Step 2: Header with Logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.little_lemon_logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(200.dp, 50.dp)
            )
        }

        // Static prompt text area
        Text(
            text = "Let's get to know you",
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryGreen)
                .padding(vertical = 40.dp)
        )

        // Step 4: Personal Information Inputs
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Personal information",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 40.dp)
            )

            // First Name
            Text(text = "First name", fontSize = 12.sp)
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                placeholder = { Text("Tilly") }
            )

            // Last Name
            Text(text = "Last name", fontSize = 12.sp)
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                placeholder = { Text("Doe") }
            )

            // Email
            Text(text = "Email", fontSize = 12.sp)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                placeholder = { Text("tillydoe@example.com") }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Step 5: Register Button
        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(
                        context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    sharedPreferences.edit {
                        putString("firstName", firstName)
                            .putString("lastName", lastName)
                            .putString("email", email)
                            .putBoolean("userRegistered", true)
                    }

                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Register", color = Color.Black)
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {

}