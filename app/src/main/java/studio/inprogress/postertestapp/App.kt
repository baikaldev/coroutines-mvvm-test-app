package studio.inprogress.postertestapp

import android.app.Application
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.postertestapp.di.factory.AppComponentFactory
import studio.inprogress.postertestapp.di.factory.PosterBlComponentFactory
import studio.inprogress.postertestapp.di.factory.PosterUiComponentFactory
import timber.log.Timber

class App : Application() {

    companion object {

        private lateinit var COMPONENT_STORAGE_INSTANCE: ComponentStorage

        fun getComponentStorage(): ComponentStorage {
            return COMPONENT_STORAGE_INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initComponentStorage()
    }

    private fun initComponentStorage() {
        COMPONENT_STORAGE_INSTANCE = ComponentStorage().apply {
            withLogger { Timber.d(it) }
        }
        COMPONENT_STORAGE_INSTANCE.registerComponentFactory(
            AppComponentFactory(this),
            PosterBlComponentFactory(),
            PosterUiComponentFactory()
        )
    }
}