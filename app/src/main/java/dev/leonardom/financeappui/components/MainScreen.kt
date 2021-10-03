package dev.leonardom.financeappui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import dev.leonardom.financeappui.model.Stock

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    randomStartPoint: Offset,
    points: List<Offset>,
    months: List<String>,
    stockList: List<Stock>,
    date: String
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val bottomSheetPeekHeight = remember { mutableStateOf(80.dp) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val maxScaffoldHeight = maxHeight * 0.4f

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = bottomSheetPeekHeight.value,
            sheetShape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
            sheetContent = {
                StockList(
                    bottomSheetPeekHeight = bottomSheetPeekHeight.value,
                    stockList = stockList,
                    date = date,
                    maxHeight = maxScaffoldHeight
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TopGraph()

                StockPrice(
                    date = date
                )

                StockGraph(
                    points = points,
                    randomStartPoint = randomStartPoint,
                    months = months
                )

                Box(
                    modifier = Modifier.fillMaxWidth().height(bottomSheetPeekHeight.value)
                )
            }
        }
    }

}










