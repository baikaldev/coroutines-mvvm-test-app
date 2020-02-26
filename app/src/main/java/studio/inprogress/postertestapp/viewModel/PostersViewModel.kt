package studio.inprogress.postertestapp.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity
import studio.inprogress.postertestapp.model.interactor.IPosterInteractor

class PostersViewModel(private val posterInteractor: IPosterInteractor) : ViewModel() {

    private val posterListLiveData: MutableLiveData<Result<List<PosterEntity>>> = MutableLiveData()
    private val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var yearFilter: Int? = null

    init {
        loadAllPosters()
    }

    fun setYearFilter(yearFilter: Int?) {
        this.yearFilter = yearFilter
        loadPostersFromCache()
    }

    private fun loadPostersFromCache() {
        viewModelScope.launch {
            try {
                val postersFromCache = posterInteractor.loadPostersFromCache()
                posterListLiveData.postValue(
                    Result.Success(
                        postersFromCache.filter(::filterByYear)
                    )
                )
            } catch (e: Throwable) {
                posterListLiveData.postValue(Result.Error(e))
            }
        }
    }

    fun loadAllPosters() {
        viewModelScope.launch {
            try {
                progressLiveData.postValue(true)

                val cachedPosters = posterInteractor.loadPostersFromCache()
                posterListLiveData.postValue(Result.Success(cachedPosters.filter(::filterByYear)))

                val postersFromNet = posterInteractor.loadPostersFromNet()
                posterListLiveData.postValue(Result.Success(postersFromNet.filter(::filterByYear)))

                progressLiveData.postValue(false)
            } catch (e: Throwable) {
                posterListLiveData.postValue(Result.Error(e))
            }
        }
    }

    private fun filterByYear(dto: PosterEntity): Boolean {
        return yearFilter?.let {
            dto.year == it
        } ?: true
    }

    fun observePosterList(owner: LifecycleOwner, observer: Observer<Result<List<PosterEntity>>>) {
        posterListLiveData.observe(owner, observer)
    }

    fun observePosterLoadingProgress(owner: LifecycleOwner, observer: Observer<Boolean>) {
        progressLiveData.observe(owner, observer)
    }
}