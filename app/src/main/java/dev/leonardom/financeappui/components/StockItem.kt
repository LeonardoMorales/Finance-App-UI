package dev.leonardom.financeappui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import dev.leonardom.financeappui.model.Stock
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

@Composable
fun StockItem(
    stock: Stock
) {
    val date = Date(stock.date)
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", java.util.Locale.ROOT)
    val absStockPrice = abs(stock.price)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(stock.color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = stock.icon),
                contentDescription = stock.company,
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = stock.company,
                style = TextStyle(
                    color = Color.White
                )
            )
            Text(
                text = dateFormat.format(date).capitalize(Locale.current),
                style = TextStyle(
                    color = Color.White.copy(alpha = 0.4f)
                )
            )
        }

        Text(
            modifier = Modifier.wrapContentWidth(Alignment.End),
            text = if (stock.price >= 0) {
                "$$absStockPrice"
            } else {
                "- $$absStockPrice"
            },
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}