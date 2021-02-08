package com.example.bitclient.data.network.datamodels.pagingmodels

import com.google.gson.annotations.SerializedName

abstract class PaginatedResponse<DataModel : Any>(
    @SerializedName("values")
    val values: List<DataModel>,
    @SerializedName("prev")
    val previousPage: String? = null,
    @SerializedName("next")
    val nextPage: String? = null
)