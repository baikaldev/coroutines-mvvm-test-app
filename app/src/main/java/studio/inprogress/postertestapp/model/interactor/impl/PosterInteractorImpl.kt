package studio.inprogress.postertestapp.model.interactor.impl

import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity
import studio.inprogress.postertestapp.model.interactor.IPosterInteractor
import studio.inprogress.postertestapp.model.repository.IPosterRepository

class PosterInteractorImpl(
    private val posterRepository: IPosterRepository
) : IPosterInteractor {
    override suspend fun loadPostersFromNet(): List<PosterEntity> {
        return posterRepository.loadPosters()
    }

    override suspend fun loadPostersFromCache(): List<PosterEntity> {
        return posterRepository.loadPostersFromCache()
    }
}