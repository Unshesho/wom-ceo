package com.womceo.breeds.data.model

import com.google.gson.annotations.SerializedName
import com.womceo.breeds.data.model.Constants.MESSAGE

data class RemoteBreedImages(
    @SerializedName(MESSAGE) val breedList: List<String?>?
)
