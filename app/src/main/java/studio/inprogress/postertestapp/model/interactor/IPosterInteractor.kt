package studio.inprogress.postertestapp.model.interactor

import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity

interface IPosterInteractor {

    suspend fun loadPostersFromNet(): List<PosterEntity>

    suspend fun loadPostersFromCache(): List<PosterEntity>
}