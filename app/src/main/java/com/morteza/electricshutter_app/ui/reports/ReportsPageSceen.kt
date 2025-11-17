package com.morteza.electricshutter_app.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.electricshutter_app.data.models.CustomerReportEntity
import com.electricshutter_app.viewmodel.ReportsViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReportsPageScreen(navController: NavController, viewModel: ReportsViewModel) {
    val reports by viewModel.reports.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val nf = NumberFormat.getInstance(Locale("fa", "IR"))
    val sdf = SimpleDateFormat("yyyy/MM/dd", Locale("fa"))

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(scrollState)
    ) {
        Text("گزارشات مشتریان", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                searchQuery = it
                viewModel.searchReports(searchQuery)
            },
            label = { Text("جستجوی نام مشتری") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        reports.forEach { report ->
            Card(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("${report.customerName} - ${sdf.format(report.date)}", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("ارتفاع: ${report.height} سانتی‌متر")
                    Text("عرض: ${report.width} سانتی‌متر")
                    Text("مساحت: ${report.area} متر مربع")
                    Text("تیغه: ${report.bladeName} - ${nf.format(report.bladePrice)} تومان")
                    Text("موتور: ${report.motorName} - ${nf.format(report.motorPrice)} تومان")
                    Text("شفت: ${report.shaftName} - ${nf.format(report.shaftPrice)} تومان")
                    Text("قوطی: ${report.boxName} - ${nf.format(report.boxPrice)} تومان")
                    Text("هزینه نصب: ${nf.format(report.installationCost)}")
                    Text("هزینه جوشکاری: ${nf.format(report.weldingCost)}")
                    Text("کرایه حمل: ${nf.format(report.transportCost)}")
                    if (report.extraFeatures.isNotEmpty()) {
                        Text("امکانات بیشتر:")
                        report.extraFeatures.forEach { extra ->
                            Text(" - ${extra.name} : ${nf.format(extra.price)} تومان")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("قیمت نهایی: ${nf.format(report.totalPrice)} تومان", style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(onClick = { /* مشاهده جزئیات کامل - در اینجا همه اطلاعات نمایش داده می‌شود */ }) {
                            Text("مشاهده")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { viewModel.deleteReport(report) }) {
                            Text("حذف")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("main") }) {
            Text("بازگشت به صفحه اصلی")
        }
    }

}
