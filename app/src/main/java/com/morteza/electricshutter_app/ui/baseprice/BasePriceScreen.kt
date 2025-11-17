package com.morteza.shuttercalculator.ui.baseprice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.electricshutter_app.data.models.CostEntity
import com.electricshutter_app.data.models.MoreFeature
import com.electricshutter_app.viewmodel.BasePriceViewModel
import java.text.NumberFormat
import java.util.*

@Composable
fun BasePriceScreen(navController: NavController, viewModel: BasePriceViewModel) {
    val blades by viewModel.blades.collectAsState()
    val motors by viewModel.motors.collectAsState()
    val shafts by viewModel.shafts.collectAsState()
    val boxes by viewModel.boxes.collectAsState()
    val extras by viewModel.extras.collectAsState()
    val costs by viewModel.costs.collectAsState()

    val installationCost = remember { mutableStateOf(costs.installation) }
    val weldingCost = remember { mutableStateOf(costs.welding) }
    val transportCost = remember { mutableStateOf(costs.transport) }

    val scrollState = rememberScrollState()
    val nf = NumberFormat.getInstance(Locale("fa", "IR"))

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(scrollState)
    ) {
        Text("صفحه قیمت پایه", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // --- تیغه ---
        PriceListCard(
            title = "افزودن تیغه",
            items = blades,
            onAdd = { viewModel.addBlade(MoreFeature("تیغه جدید", 0)) },
            onDelete = { viewModel.deleteBlade(it) },
            onEdit = { index, newItem ->
                viewModel.deleteBlade(blades[index])
                viewModel.addBlade(newItem)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- موتور ---
        PriceListCard(
            title = "افزودن موتور",
            items = motors,
            onAdd = { viewModel.addMotor(MoreFeature("موتور جدید", 0)) },
            onDelete = { viewModel.deleteMotor(it) },
            onEdit = { index, newItem ->
                viewModel.deleteMotor(motors[index])
                viewModel.addMotor(newItem)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- شفت ---
        PriceListCard(
            title = "افزودن شفت",
            items = shafts,
            onAdd = { viewModel.addShaft(MoreFeature("شفت جدید", 0)) },
            onDelete = { viewModel.deleteShaft(it) },
            onEdit = { index, newItem ->
                viewModel.deleteShaft(shafts[index])
                viewModel.addShaft(newItem)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- قوطی ---
        PriceListCard(
            title = "افزودن قوطی",
            items = boxes,
            onAdd = { viewModel.addBox(MoreFeature("قوطی جدید", 0)) },
            onDelete = { viewModel.deleteBox(it) },
            onEdit = { index, newItem ->
                viewModel.deleteBox(boxes[index])
                viewModel.addBox(newItem)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- امکانات بیشتر ---
        PriceListCard(
            title = "افزودن امکانات بیشتر",
            items = extras,
            onAdd = { viewModel.addExtra(MoreFeature("امکان جدید", 0)) },
            onDelete = { viewModel.deleteExtra(it) },
            onEdit = { index, newItem ->
                viewModel.deleteExtra(extras[index])
                viewModel.addExtra(newItem)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- هزینه‌های اجرایی ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("هزینه‌های اجرایی")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = installationCost.value.toString(),
                    onValueChange = { installationCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("هزینه نصب") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = weldingCost.value.toString(),
                    onValueChange = { weldingCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("هزینه جوشکاری") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = transportCost.value.toString(),
                    onValueChange = { transportCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("کرایه حمل") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    viewModel.saveCosts(CostEntity(
                        installation = installationCost.value,
                        welding = weldingCost.value,
                        transport = transportCost.value
                    ))
                }) {
                    Text("ذخیره هزینه‌ها")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("main") }) {
            Text("بازگشت به صفحه اصلی")
        }
    }
}

@Composable
fun PriceListCard(
    title: String,
    items: List<MoreFeature>,
    onAdd: () -> Unit,
    onDelete: (MoreFeature) -> Unit,
    onEdit: (Int, MoreFeature) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            items.forEachIndexed { index, item ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${item.name} - ${item.price} تومان")
                    Row {
                        Button(onClick = { onEdit(index, item) }) { Text("ویرایش") }
                        Spacer(modifier = Modifier.width(4.dp))
                        Button(onClick = { onDelete(item) }) { Text("حذف") }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onAdd) { Text("افزودن") }
        }
    }
}