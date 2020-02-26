package studio.inprogress.postertestapp.di

import dagger.Component
import studio.inprogress.postertestapp.di.modules.ApiModule
import studio.inprogress.postertestapp.di.modules.DatabaseModule
import studio.inprogress.postertestapp.di.modules.NetworkModule
import studio.inprogress.postertestapp.di.modules.RepositoryModule
import studio.inprogress.postertestapp.model.repository.IPosterRepository
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class
    ]
)
@Singleton
interface AppComponent {
    fun providePosterRepository(): IPosterRepository
}