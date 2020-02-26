package studio.inprogress.postertestapp.di

import dagger.Component
import dagger.Module
import dagger.Provides
import studio.inprogress.postertestapp.di.scope.UiScope
import studio.inprogress.postertestapp.model.interactor.IPosterInteractor
import studio.inprogress.postertestapp.ui.activity.PostersActivity
import studio.inprogress.postertestapp.viewModel.factory.PostersViewModelFactory
import javax.inject.Provider

@Component(
    modules = [PosterUiModule::class],
    dependencies = [PostersBlComponent::class]
)
@UiScope
interface PosterUiComponent {
    fun inject(posterActivity: PostersActivity)
}

@Module
class PosterUiModule {
    @Provides
    fun providePosterViewModelFactory(posterInteractor: Provider<IPosterInteractor>): PostersViewModelFactory {
        return PostersViewModelFactory(posterInteractor)
    }
}