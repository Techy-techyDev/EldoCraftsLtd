package screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

sealed class Screen(var title: String, var icon: ImageVector, var screen_route: String){
    object Home : Screen("Home", Icons.Filled.Home,"home")
    object Clients : Screen("Clients", Icons.Default.List,"clients")
    object Discover : Screen("Discover", Icons.Default.Search,"discover")
    object Settings: Screen("Settings", Icons.Filled.Settings,"settings")
}