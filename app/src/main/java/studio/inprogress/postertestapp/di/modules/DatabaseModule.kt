package studio.inprogress.postertestapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import studio.inprogress.postertestapp.model.dataSource.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "poster_test_app_db")
            .build()
    }
}