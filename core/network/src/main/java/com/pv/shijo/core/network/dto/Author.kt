package com.pv.shijo.core.network.dto


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("profilePicture")
    val profilePicture: String?,
    @SerializedName("username")
    val username: String?
)