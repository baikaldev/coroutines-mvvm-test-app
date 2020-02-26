package studio.inprogress.postertestapp.model.dataSource.remote.dto

import com.google.gson.annotations.SerializedName

class PosterDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("poster")
    val imageUrl: String,

    @SerializedName("year")
    val year: Int
)