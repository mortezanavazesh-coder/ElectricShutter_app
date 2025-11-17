package com.morteza.shuttercalculator.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.electricshutter_app.data.models.CustomerReportEntity

@Composable
fun SaveCalculationDialog(
    initialReport: CustomerReportEntity,
    show: Boolean,
    onDismiss: () -> Unit,
    onSave: (CustomerReportEntity) -> Unit
) {
    if (!show) return

    var name by remember { mutableStateOf(initialReport.customerName) }
    var phone by remember { mutableStateOf(initialReport.customerPhone ?: "") }
    val isValid = name.trim().isNotEmpty()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("ذخیره محاسبات") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("نام مشتری (الزامی)") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("شماره تماس (اختیاری)") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (!isValid) return@TextButton
                    val reportToSave = initialReport.copy(
                        customerName = name.trim(),
                        customerPhone = phone.trim().ifEmpty { null }
                    )
                    onSave(reportToSave)
                }
            ) { Text("ذخیره") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("انصراف") }
        }
    )
}