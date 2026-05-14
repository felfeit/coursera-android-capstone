package com.quiraadev.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.quiraadev.littlelemon.data.local.MenuDatabase
import com.quiraadev.littlelemon.ui.routes.Home
import com.quiraadev.littlelemon.ui.routes.Onboarding
import com.quiraadev.littlelemon.ui.routes.Profile
import com.quiraadev.littlelemon.ui.screens.HomeScreen
import com.quiraadev.littlelemon.ui.screens.OnboardingScreen
import com.quiraadev.littlelemon.ui.screens.ProfileScreen

@Composable
fun Navigation(navController: NavHostController, database: MenuDatabase) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val isUserLoggedIn = sharedPreferences.getString("email", "")?.isNotBlank() ?: false

    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) Home.route else Onboarding.route
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Home.route) {
            HomeScreen(navController = navController, database = database)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}