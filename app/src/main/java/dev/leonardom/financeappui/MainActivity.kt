package dev.leonardom.financeappui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.capitalize
import dev.leonardom.financeappui.components.MainScreen
import dev.leonardom.financeappui.model.Stock
import dev.leonardom.financeappui.ui.theme.BLUE200
import dev.leonardom.financeappui.ui.theme.FinanceAppUITheme
import dev.leonardom.financeappui.ui.theme.PINK200
import dev.leonardom.financeappui.ui.theme.RED600
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

private val months = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)

private val stocks =  listOf(
    Stock(
        company = "Dribbble",
        icon = R.drawable.ic_dribbble_logo,
        color = PINK200,
        price = 66.43,
        date = System.currentTimeMillis()
    ),
    Stock(
        company = "Skype",
        icon = R.drawable.ic_skype_logo,
        color = BLUE200,
        price = -32.60,
        date = System.currentTimeMillis()
    ),
    Stock(
        company = "Youtube Premium",
        icon = R.drawable.ic_youtube_logo,
        color = RED600,
        price = -12.00,
        date = System.currentTimeMillis()
    )
)

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val points = parse(this)
        val randomStartPoint = points.shuffled().first()

        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ROOT)
        val date = dateFormat.format(time).capitalize(Locale.ROOT)

        setContent {
            FinanceAppUITheme {
                MainScreen(
                    randomStartPoint = randomStartPoint,
                    points = points,
                    months = months,
                    stockList = stocks,
                    date = date
                )
            }
        }
    }
}

private fun parse(context: Context): List<Offset> {
    val json = context.assets.open("coordinator.json").bufferedReader().use { it.readLine() }
    val array = JSONArray(json)
    val points = mutableListOf<Offset>()

    for (i in 0 until array.length()) {
        val coordinate = array.optJSONArray(i)
        val x = coordinate.optDouble(0).toFloat()
        val y = coordinate.optDouble(1).toFloat()
        points += Offset(x, y)
    }

    return points
}