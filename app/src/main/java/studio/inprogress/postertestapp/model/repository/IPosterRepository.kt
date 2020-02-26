package studio.inprogress.postertestapp.model.repository

import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity

interface IPosterRepository {


    suspend fun loadPosters(): List<PosterEntity>

    suspend fun loadPostersFromCache(): List<PosterEntity>
}