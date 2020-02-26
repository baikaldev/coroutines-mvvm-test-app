package studio.inprogress.postertestapp.di

import dagger.Component
import dagger.Module
import dagger.Provides
import studio.inprogress.postertestapp.di.scope.PosterScope
import studio.inprogress.postertestapp.model.interactor.IPosterInteractor
import studio.inprogress.postertestapp.model.interactor.impl.PosterInteractorImpl
import studio.inprogress.postertestapp.model.repository.IPosterRepository

@Component(
    dependencies = [AppComponent::class],
    modules = [PosterBlModule::class]
)
@PosterScope
interface PostersBlComponent {

    fun providePosterInteractor(): IPosterInteractor

}

@Module
class PosterBlModule {

    @Provides
    @PosterScope
    fun providePosterInteractor(posterRepository: IPosterRepository): IPosterInteractor {
        return PosterInteractorImpl(posterRepository)
    }


}