package com.oishikenko.android.recruitment.feature.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.R
import java.net.URLEncoder

@Composable
fun RecipeListItem(
    cookingRecord: CookingRecord,
    onClick: (String) -> Unit,
) {
    val value = CookingRecord.serialize(cookingRecord)
    val encodedValue = URLEncoder.encode(value, "utf-8")

    val recipeTypeName: String = when (cookingRecord.recipeType) {
        "main_dish" -> {
            stringResource(id = R.string.recipe_type_main_dish)
        }
        "side_dish" -> {
            stringResource(id = R.string.recipe_type_side_dish)
        }
        "soup" -> {
            stringResource(id = R.string.recipe_type_soup)
        }
        else -> {
            ""
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick(encodedValue)
            }
    ) {
        AsyncImage(
            model = cookingRecord.imageUrl,
            contentDescription = cookingRecord.comment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
            placeholder = debugPlaceholder(debugPreview = R.drawable.recipe_1),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = recipeTypeName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black_text)
            )
            Text(
                text = cookingRecord.recordedAt,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.gray)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRecipeListItem() {
    RecipeListItem(
        cookingRecord = CookingRecord(
            imageUrl= "",
            comment = "豚肉のコクとごぼうの香り、野菜の甘みで奥行きのある味わい。",
            recipeType = "soup",
            recordedAt = "2018-05-01 17:57:31"
        ),
    ) {}
}