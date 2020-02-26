package studio.inprogress.postertestapp.di.modules

import dagger.Module
import dagger.Provides
import studio.inprogress.postertestapp.model.dataSource.database.AppDatabase
import studio.inprogress.postertestapp.model.dataSource.remote.IPostersApi
import studio.inprogress.postertestapp.model.repository.IPosterRepository
import studio.inprogress.postertestapp.model.repository.impl.PosterRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePostersRepository(postersApi: IPostersApi, appDatabase: AppDatabase): IPosterRepository {
        return PosterRepositoryImpl(postersApi, appDatabase.getPosterDao())
    }
}