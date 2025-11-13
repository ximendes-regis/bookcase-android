package br.com.ximendesindustries.bookcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.ximendesindustries.bookcase.core.util.JsonNavHelper
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.ui.bookdetail.BookDetailScreen
import br.com.ximendesindustries.bookcase.ui.home.HomeScreen

@Composable
fun AppNavHost(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.HOME.route
    ) {
        composable(NavRoute.HOME.route) {
            HomeScreen(modifier) { book ->
                    val arg = JsonNavHelper.toRouteArg(book)
                    navController.navigate("${NavRoute.BOOK_DETAIL.route}/$arg")
            }
        }

        composable(
            route = "${NavRoute.BOOK_DETAIL.route}/{bookJson}",
            arguments = listOf(navArgument("bookJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("bookJson") ?: return@composable
            val book = JsonNavHelper.fromRouteArg<Book>(encoded) ?: return@composable
            BookDetailScreen(book = book)
        }
    }
}
