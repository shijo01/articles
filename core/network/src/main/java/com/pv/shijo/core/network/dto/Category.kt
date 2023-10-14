package com.pv.shijo.core.network.dto


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)