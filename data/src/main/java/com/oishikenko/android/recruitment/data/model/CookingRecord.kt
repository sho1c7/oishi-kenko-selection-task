package com.oishikenko.android.recruitment.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.Moshi

data class CookingRecord(
    @Json(name = "comment")
    val comment: String,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "recipe_type")
    val recipeType: String,
    @Json(name = "recorded_at")
    val recordedAt: String
) {
    companion object {
        fun parseValue(value: String?): CookingRecord? {
            if (value == null){
                return null
            }
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(CookingRecord::class.java)
            return adapter.fromJson(value)
        }
    }
}
