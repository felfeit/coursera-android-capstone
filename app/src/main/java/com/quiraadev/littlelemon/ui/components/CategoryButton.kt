package com.quiraadev.littlelemon.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.quiraadev.littlelemon.ui.theme.HighlightGray
import com.quiraadev.littlelemon.ui.theme.PrimaryGreen

@Composable
fun CategoryButton(category: String, isSelected: Boolean, onSelect: (String) -> Unit) {
    Button(
        onClick = { onSelect(category) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) PrimaryGreen else HighlightGray,
            contentColor = if (isSelected) Color.White else PrimaryGreen
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(end = 8.dp)
    ) {

        Text(text = category.replaceFirstChar { it.uppercase() }, fontWeight = FontWeight.Bold)
    }
}