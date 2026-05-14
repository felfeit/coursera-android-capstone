package com.quiraadev.littlelemon.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.core.content.edit
import com.quiraadev.littlelemon.R
import com.quiraadev.littlelemon.ui.components.LabelAndText
import com.quiraadev.littlelemon.ui.routes.Home
import com.quiraadev.littlelemon.ui.routes.Onboarding
import com.quiraadev.littlelemon.ui.theme.PrimaryYellow

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    // Retrieve data from Shared Preferences
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step 1: Header with Logo
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

        // Step 2: Display User Data
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Profile information:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 30.dp)
            )

            // First Name Display
            LabelAndText(label = "First name", value = firstName)
            // Last Name Display
            LabelAndText(label = "Last name", value = lastName)
            // Email Display
            LabelAndText(label = "Email", value = email)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                // Clear all data from shared preferences
                sharedPreferences.edit { clear() }

                // Navigate back to Onboarding and clear backstack
                navController.navigate(Onboarding.route) {
                    popUpTo(Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow), // Yellow
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Log out", color = Color.Black)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
//    val navController = rememberNavController()
//    ProfileScreen(navController = navController)
}