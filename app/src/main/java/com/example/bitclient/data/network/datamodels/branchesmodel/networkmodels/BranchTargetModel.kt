package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.google.gson.annotations.SerializedName

data class BranchTargetModel(
    @SerializedName("hash")
    val branchHash: String
)