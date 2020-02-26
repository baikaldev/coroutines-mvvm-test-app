package studio.inprogress.postertestapp.di.factory

import android.content.Context
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory
import studio.inprogress.postertestapp.di.AppComponent
import studio.inprogress.postertestapp.di.DaggerAppComponent
import studio.inprogress.postertestapp.di.modules.DatabaseModule

class AppComponentFactory(private val appContext: Context): IComponentFactory<AppComponent> {
    override fun create(componentStorage: ComponentStorage): AppComponent {
        return DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(appContext))
            .build()
    }

    override fun getName(): String {
        return AppComponent::class.java.simpleName
    }

    override fun isReleasable(): Boolean {
        return false
    }
}