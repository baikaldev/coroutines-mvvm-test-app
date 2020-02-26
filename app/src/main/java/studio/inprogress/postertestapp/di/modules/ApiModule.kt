package studio.inprogress.postertestapp.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import studio.inprogress.postertestapp.model.dataSource.remote.IPostersApi
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providePosterApi(retrofit: Retrofit): IPostersApi {
        return retrofit.create(IPostersApi::class.java)
    }
}