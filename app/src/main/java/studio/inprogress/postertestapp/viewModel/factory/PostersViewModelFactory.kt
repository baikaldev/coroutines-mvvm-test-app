package studio.inprogress.postertestapp.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import studio.inprogress.postertestapp.model.interactor.IPosterInteractor
import studio.inprogress.postertestapp.viewModel.PostersViewModel
import javax.inject.Provider

class PostersViewModelFactory(
    private val posterInteractor: Provider<IPosterInteractor>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostersViewModel(posterInteractor.get()) as T
    }
}