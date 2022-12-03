package com.oishikenko.android.recruitment

import android.os.Bundle
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
import com.oishikenko.android.recruitment.ui.theme.RecruitmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "listScreen") {
                composable(route = "listScreen") {
                    RecruitmentTheme {
                        RecipeListScreen()
                    }
                }
                composable(
                    route = "RecipeScreen/{value}",
                    arguments = listOf(navArgument("value") { type = NavType.Companion.StringType })
                ) {
                    val value = it.arguments?.getString("value")
                    val cookingRecord: CookingRecord? = CookingRecord.parseValue(value)
                    RecruitmentTheme {
                        RecipeScreen(cookingRecord)
                    }
                }
            }
        }
    }
}
