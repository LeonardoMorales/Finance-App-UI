package dev.leonardom.financeappui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.leonardom.financeappui.model.Stock

@Composable
fun StockList(
    bottomSheetPeekHeight: Dp,
    stockList: List<Stock>,
    date: String,
    maxHeight: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxHeight)
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Divider(
            modifier = Modifier
                .width(42.dp)
                .padding(top = 8.dp, bottom = 16.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            thickness = 3.dp,
            color = Color.White
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomSheetPeekHeight / 2),
            text = date,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(stockList) {
                StockItem(it)
            }
        }
    }
}

















