package com.quiraadev.littlelemon.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.quiraadev.littlelemon.data.local.entities.MenuEntity


@Composable
fun MenuList(items: List<MenuEntity>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        items(items) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(item.title, fontWeight = FontWeight.Bold)
                    Text(item.description, color = Color.Gray, maxLines = 2)
                    Text("$${item.price}", fontWeight = FontWeight.SemiBold)
                }
                AsyncImage(
                    model = item.image,
                    contentDescription = item.title,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(start = 8.dp)
                )
            }
            HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
        }
    }
}