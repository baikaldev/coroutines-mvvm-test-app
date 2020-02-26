package studio.inprogress.postertestapp.model.dataSource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import studio.inprogress.postertestapp.model.dataSource.remote.dto.PosterDto

@Entity(tableName = "poster")
data class PosterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "year")
    val year: Int
) {
    companion object {
        fun from(posterDto: PosterDto): PosterEntity{
            return PosterEntity(
                id = posterDto.id,
                imageUrl = posterDto.imageUrl,
                year =  posterDto.year
            )
        }

        fun from(posterDtoList: List<PosterDto>): List<PosterEntity> {
            return posterDtoList.asSequence()
                .map { from(it) }
                .toList()
        }
    }
}