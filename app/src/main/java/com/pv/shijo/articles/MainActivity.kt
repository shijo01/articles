package com.pv.shijo.articles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pv.shijo.articles.ui.ArticleDetailRoute
import com.pv.shijo.articles.ui.ArticleListRoute
import com.pv.shijo.theme.ArticlesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleViewModel by viewModels<ArticleViewModel>()
        setContent {
            ArticlesTheme(
                androidTheme = false,
                disableDynamicTheming = false,
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(12.dp)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "article") {
                        composable("article") {
                            ArticleListRoute(modifier = Modifier.fillMaxSize(),
                                articleViewModel = articleViewModel,
                                navigateToArticleDetail = {
                                    navController.navigate("article/${it.id}")
                                })
                        }
                        composable("article/{articleId}") { navBackStackEntry ->
                            ArticleDetailRoute(articleViewModel = articleViewModel,
                                articleId = navBackStackEntry.arguments?.getString(
                                    "articleId", ""
                                ) ?: "",
                                onBackClick = {
                                    navController.popBackStack()
                                })
                        }
                    }
                }
            }
        }
    }
}

