package screen.navigation

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Home.screen_route) {
        composable(Screen.Home.screen_route) {
            HomeScreen(viewModel = HomeScreenViewModel())
        }
        composable(Screen.Crafts.screen_route) {

        }
        composable(Screen.Profile.screen_route) {
            ProfileScreen(viewModel = ProfileScreenViewModel())
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Crafts,
        Screen.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor =MaterialTheme.colorScheme.onBackground,
        contentColor = Color.teal_200
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = Color.White
                ) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                },
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}