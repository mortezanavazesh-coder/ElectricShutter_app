package com.morteza.shuttercalculator.ui.main

import androidx.compose.foundation.ScrollState
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
import com.electricshutter_app.data.models.MoreFeature
import com.electricshutter_app.viewmodel.BasePriceViewModel
import java.text.NumberFormat
import java.util.*

@Composable
fun MainScreen(navController: NavController, viewModel: BasePriceViewModel) {

    val blades by viewModel.blades.collectAsState()
    val motors by viewModel.motors.collectAsState()
    val shafts by viewModel.shafts.collectAsState()
    val boxes by viewModel.boxes.collectAsState()
    val extras by viewModel.extras.collectAsState()
    val costs by viewModel.costs.collectAsState()

    var heightText by remember { mutableStateOf("") }
    var widthText by remember { mutableStateOf("") }

    var selectedBlade by remember { mutableStateOf<MoreFeature?>(null) }
    var selectedMotor by remember { mutableStateOf<MoreFeature?>(null) }
    var selectedShaft by remember { mutableStateOf<MoreFeature?>(null) }
    var selectedBox by remember { mutableStateOf<MoreFeature?>(null) }

    val installationCost = remember { mutableStateOf(costs.installation) }
    val weldingCost = remember { mutableStateOf(costs.welding) }
    val transportCost = remember { mutableStateOf(costs.transport) }

    var extraChecked by remember { mutableStateMapOf<MoreFeature, Boolean>() }

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(scrollState)
    ) {
        Text(text = "محاسبه قیمت کرکره", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // --- ارتفاع و عرض ---
        OutlinedTextField(
            value = heightText,
            onValueChange = { heightText = it },
            label = { Text("ارتفاع (سانتی‌متر)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = widthText,
            onValueChange = { widthText = it },
            label = { Text("عرض (سانتی‌متر)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- محاسبات خودکار ---
        val height = heightText.toDoubleOrNull()?.div(100) ?: 0.0
        val width = widthText.toDoubleOrNull()?.div(100) ?: 0.0
        val area = height * width
        val motorPower = area * 10

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("مساحت: %.2f متر مربع".format(area))
                Text("توان موتور: %.2f کیلو".format(motorPower))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val nf = NumberFormat.getInstance(Locale("fa", "IR"))

        // --- انتخاب تیغه ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("انتخاب تیغه")
                DropdownMenuBox(items = blades, selected = selectedBlade, onSelect = { selectedBlade = it })
                selectedBlade?.let {
                    Text("قیمت پایه: ${nf.format(it.price)} تومان")
                    Text("قیمت کرکره: ${nf.format((area * it.price))} تومان")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- انتخاب موتور ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("انتخاب موتور")
                DropdownMenuBox(items = motors, selected = selectedMotor, onSelect = { selectedMotor = it })
                selectedMotor?.let { Text("قیمت موتور: ${nf.format(it.price)} تومان") }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- انتخاب شفت ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("انتخاب شفت")
                DropdownMenuBox(items = shafts, selected = selectedShaft, onSelect = { selectedShaft = it })
                selectedShaft?.let { Text("قیمت شفت: ${nf.format(width * it.price)} تومان") }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- انتخاب قوطی ---
        var boxEnabled by remember { mutableStateOf(true) }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Checkbox(checked = boxEnabled, onCheckedChange = { boxEnabled = it })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("انتخاب قوطی")
                }
                if (boxEnabled) {
                    DropdownMenuBox(items = boxes, selected = selectedBox, onSelect = { selectedBox = it })
                    selectedBox?.let { Text("قیمت قوطی: ${nf.format((height - 0.3) * 2 * it.price)} تومان") }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- هزینه‌های اجرایی ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("هزینه‌های اجرایی")
                OutlinedTextField(
                    value = installationCost.value.toString(),
                    onValueChange = { installationCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("هزینه نصب") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = weldingCost.value.toString(),
                    onValueChange = { weldingCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("هزینه جوشکاری") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = transportCost.value.toString(),
                    onValueChange = { transportCost.value = it.toLongOrNull() ?: 0L },
                    label = { Text("کرایه حمل") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    viewModel.saveCosts(
                        com.electricshutter_app.data.models.CostEntity(
                            installation = installationCost.value,
                            welding = weldingCost.value,
                            transport = transportCost.value
                        )
                    )
                }) { Text("ذخیره هزینه‌ها") }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- امکانات بیشتر ---
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("امکانات بیشتر")
                extras.forEach { feature ->
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        val checked = extraChecked[feature] ?: false
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { extraChecked[feature] = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${feature.name} - ${nf.format(feature.price)} تومان")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- ریز محاسبات ---
        val priceBlade = selectedBlade?.price?.let { area * it.price } ?: 0.0
        val priceMotor = selectedMotor?.price ?: 0L
        val priceShaft = selectedShaft?.price?.let { width * it.price } ?: 0.0
        val priceBox = if (boxEnabled) selectedBox?.price?.let { (height - 0.3) * 2 * it.price } ?: 0.0 else 0.0
        val extrasPrice = extras.filter { extraChecked[it] == true }.sumOf { it.price }
        val totalPrice =
            priceBlade.toLong() + priceMotor + priceShaft.toLong() + priceBox.toLong() + installationCost.value + weldingCost.value + transportCost.value + extrasPrice

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ریز محاسبات:")
                Text("قیمت کرکره: ${nf.format(priceBlade.toLong())}")
                Text("قیمت موتور: ${nf.format(priceMotor)}")
                Text("قیمت شفت: ${nf.format(priceShaft.toLong())}")
                if (boxEnabled) Text("قیمت قوطی: ${nf.format(priceBox.toLong())}")
                Text("هزینه نصب: ${nf.format(installationCost.value)}")
                Text("هزینه جوشکاری: ${nf.format(weldingCost.value)}")
                Text("کرایه حمل: ${nf.format(transportCost.value)}")
                if (extrasPrice > 0) Text("امکانات بیشتر: ${nf.format(extrasPrice)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("قیمت نهایی: ${nf.format(totalPrice)} تومان", style = MaterialTheme.typography.headlineSmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- دکمه‌ها ---
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { navController.navigate("saveCalculation") }) { Text("ذخیره محاسبات") }
            Button(onClick = { navController.navigate("reports") }) { Text("صفحه گزارشات") }
            Button(onClick = { navController.navigate("basePrice") }) { Text("صفحه قیمت پایه") }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DropdownMenuBox(
    items: List<MoreFeature>,
    selected: MoreFeature?,
    onSelect: (MoreFeature) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(selected?.name ?: "انتخاب")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onSelect(item)
                    expanded = false
                }) {
                    Text(item.name)
                }
            }
        }
    }
}