package studio.inprogress.postertestapp.di.factory

import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory
import studio.inprogress.postertestapp.di.DaggerPostersBlComponent
import studio.inprogress.postertestapp.di.PostersBlComponent

class PosterBlComponentFactory : IComponentFactory<PostersBlComponent> {
    override fun create(componentStorage: ComponentStorage): PostersBlComponent {
        return DaggerPostersBlComponent.builder()
            .appComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String {
        return PostersBlComponent::class.java.simpleName
    }

    override fun isReleasable(): Boolean {
        return true
    }
}