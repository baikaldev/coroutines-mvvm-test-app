package studio.inprogress.postertestapp.model.dataSource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoster(poster: PosterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosters(posters: List<PosterEntity>)

    @Query("SELECT * FROM poster")
    suspend fun getAll(): List<PosterEntity>
}