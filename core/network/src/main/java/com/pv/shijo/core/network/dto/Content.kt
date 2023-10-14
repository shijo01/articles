package com.pv.shijo.core.network.dto


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("arabic")
    val arabic: String?,
    @SerializedName("english")
    val english: String?
)