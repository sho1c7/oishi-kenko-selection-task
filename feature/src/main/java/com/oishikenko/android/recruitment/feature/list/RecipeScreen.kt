package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.R

@Composable
fun RecipeScreen(
    cookingRecord: CookingRecord?
) {
    Scaffold(
        topBar =  { RecipeTopAppBar() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = cookingRecord?.imageUrl ?: "",
                        contentDescription = cookingRecord?.comment ?: "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter),
                        placeholder = debugPlaceholder(debugPreview = R.drawable.recipe_1)
                    )

                    RecipeType(
                        type = cookingRecord?.recipeType ?: "",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 8.dp, bottom = 8.dp)
                    )
                }

                Text(
                    text = cookingRecord?.comment ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black_text),
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = cookingRecord?.recordedAt ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.gray),
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun RecipeTopAppBar() {
    val navController = rememberNavController()
    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.recipe_screen_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.black_text),
            modifier = Modifier.padding(start = 20.dp)
        ) },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back button",
                    modifier = Modifier
                        .size(16.dp)
                        .padding(0.dp)
                )
            }
        },
        backgroundColor = colorResource(id = R.color.white),
        elevation = 0.dp
    )
}

@Composable
fun RecipeType(
    type: String,
    modifier: Modifier = Modifier
) {
    val imageId: Int
    val contentDesc: String
    val recipeType: Int

    when (type) {
        "main_dish" -> {
            imageId = R.drawable.main_dish
            contentDesc = "主菜/主食"
            recipeType = R.string.recipe_type_1
        }
        "side_dish" -> {
            imageId = R.drawable.side_dish
            contentDesc = "副菜"
            recipeType = R.string.recipe_type_2
        }
        "soup" -> {
            imageId = R.drawable.soup
            contentDesc = "スープ"
            recipeType = R.string.recipe_type_3
        } else -> {
            imageId = R.drawable.main_dish
            contentDesc = "主菜/主食"
            recipeType = R.string.recipe_type_1
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(shape = RoundedCornerShape(46.dp))
            .background(colorResource(id = R.color.bg_recipe_type))
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = contentDesc,
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp),
        )
        Text(
            text = stringResource(id = recipeType),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(start = 5.dp),
        )
    }
}


@Preview
@Composable
fun PreviewRecipeScreen(){
    MaterialTheme {
        RecipeScreen(cookingRecord = CookingRecord(
            imageUrl= "",
            comment = "豚肉のコクとごぼうの香り、野菜の甘みで奥行きのある味わい。",
            recipeType = "soup",
            recordedAt = "2018-05-01 17:57:31"
        ))
    }
}