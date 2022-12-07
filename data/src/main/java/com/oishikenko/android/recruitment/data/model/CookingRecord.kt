package com.oishikenko.android.recruitment.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

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
        fun parse(value: String?): CookingRecord? {
            if (value == null){
                return null
            }
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(CookingRecord::class.java)
            return adapter.fromJson(value)
        }

        fun serialize(cookingRecord: CookingRecord): String {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter = moshi.adapter(CookingRecord::class.java)
            return adapter.toJson(cookingRecord)
        }
    }
}
