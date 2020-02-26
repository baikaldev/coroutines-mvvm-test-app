package studio.inprogress.postertestapp.di.factory

import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory
import studio.inprogress.postertestapp.di.DaggerPosterUiComponent
import studio.inprogress.postertestapp.di.PosterUiComponent

class PosterUiComponentFactory: IComponentFactory<PosterUiComponent> {
    override fun create(componentStorage: ComponentStorage): PosterUiComponent {
        return DaggerPosterUiComponent.builder()
            .postersBlComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String {
        return PosterUiComponent::class.java.simpleName
    }

    override fun isReleasable(): Boolean {
        return true
    }
}