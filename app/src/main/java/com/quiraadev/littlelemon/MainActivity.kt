package com.quiraadev.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.quiraadev.littlelemon.data.local.MenuDatabase
import com.quiraadev.littlelemon.data.remote.models.MenuResponseItem
import com.quiraadev.littlelemon.data.remote.models.MenuResponse
import com.quiraadev.littlelemon.domain.mapper.toMenuEntity
import com.quiraadev.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }, contentType = ContentType.Text.Plain)

            json(Json {
                ignoreUnknownKeys = true
            }, contentType = ContentType.Application.Json)
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuDatabase::class.java,
            "database"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                val menuItems = fetchMenu()
                saveMenuToDatabase(menuItems)
            }
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController = navController, database = database)
                }
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuResponseItem> {
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val response: MenuResponse = httpClient.get(url).body()
        return response.menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuResponseItem>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuEntity() }
        database.menuDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}
