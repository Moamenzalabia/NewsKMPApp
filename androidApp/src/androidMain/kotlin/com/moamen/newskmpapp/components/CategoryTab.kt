package com.moamen.newskmpapp.components
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moamen.newskmpapp.categories.domain.Category

@Composable
fun CategoryTab(
    category: Category,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onSelect,
        text = {
            Text(
                text = category.name,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
            )
        }
    )
}
