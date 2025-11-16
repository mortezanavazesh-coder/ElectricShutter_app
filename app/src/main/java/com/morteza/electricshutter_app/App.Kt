import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourappname.ui.main.MainScreen
import com.yourappname.ui.baseprice.BasePriceScreen
import com.yourappname.ui.reports.ReportsPageScreen

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object BasePrice : Screen("base_price")
    object Reports : Screen("reports")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.BasePrice.route) { BasePriceScreen(navController) }
        composable(Screen.Reports.route) { ReportsPageScreen(navController) }
    }
}