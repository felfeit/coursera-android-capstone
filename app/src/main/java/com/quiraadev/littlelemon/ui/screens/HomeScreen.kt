package com.quiraadev.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.R
import com.quiraadev.littlelemon.data.local.MenuDatabase
import com.quiraadev.littlelemon.ui.components.CategoryButton
import com.quiraadev.littlelemon.ui.components.MenuList
import com.quiraadev.littlelemon.ui.theme.PrimaryGreen
import com.quiraadev.littlelemon.ui.theme.PrimaryYellow
import kotlin.collections.emptyList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    database: MenuDatabase
) {

    val menuItemsFromDb by database.menuDao().getAllMenuItems().observeAsState(emptyList())

    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    val filteredItems = menuItemsFromDb.filter {
        val matchesSearch = it.title.contains(searchPhrase, ignoreCase = true)
        val matchesCategory = if (selectedCategory.isEmpty()) true else it.category.equals(
            selectedCategory, ignoreCase = true
        )
        matchesSearch && matchesCategory
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Spacer(modifier = Modifier.size(50.dp))

                Image(
                    painter = painterResource(id = R.drawable.little_lemon_logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier.size(150.dp, 40.dp),
                    contentScale = ContentScale.Crop
                )


                IconButton(
                    onClick = { navController.navigate("Profile") },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.account_circle_24px),
                        contentDescription = "Profile"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .background(PrimaryGreen)
                    .padding(16.dp)
            ) {
                Text(
                    "Little Lemon",
                    fontSize = 32.sp,
                    color = PrimaryYellow,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Chicago", fontSize = 24.sp, color = Color.White)
                        Text(
                            "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            color = Color.White,
                            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Search Bar
                TextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    placeholder = { Text("Enter search phrase") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24px),
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Column(modifier = Modifier.padding(all = 16.dp)) {
                Text("Order for Delivery", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)

                val categories = menuItemsFromDb.map { it.category }.distinct()

                LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
                    items(categories) { category ->
                        CategoryButton(
                            category = category,
                            isSelected = selectedCategory == category,
                            onSelect = {
                                selectedCategory = if (selectedCategory == it) "" else it
                            }
                        )
                    }
                }
            }

            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

            // Step 5: List Menu Items
            MenuList(filteredItems)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {

}