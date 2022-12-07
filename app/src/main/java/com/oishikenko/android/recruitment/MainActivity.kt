package com.oishikenko.android.recruitment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.list.RecipeListScreen
import com.oishikenko.android.recruitment.feature.list.RecipeScreen
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "recipeList") {
                composable(route = "recipeList") {
                    RecipeListScreen() { value: String ->
                        navController.navigate("recipeDetail/$value")
                    }
                }
                composable(
                    route = "recipeDetail/{encodedValue}",
                    arguments = listOf(navArgument("encodedValue") { type = NavType.Companion.StringType })
                ) {
                    val encodedValue = it.arguments?.getString("encodedValue")
                    val value = URLDecoder.decode(encodedValue, "utf-8")
                    val cookingRecord: CookingRecord? = CookingRecord.parse(value)
                    RecipeScreen(cookingRecord) {
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}
