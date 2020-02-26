package studio.inprogress.postertestapp.model.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import studio.inprogress.postertestapp.model.dataSource.database.dao.PosterDao
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity

@Database(entities = [PosterEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getPosterDao(): PosterDao
}