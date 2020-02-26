package studio.inprogress.postertestapp.model.repository.impl

import studio.inprogress.postertestapp.model.dataSource.database.dao.PosterDao
import studio.inprogress.postertestapp.model.dataSource.database.entity.PosterEntity
import studio.inprogress.postertestapp.model.dataSource.remote.IPostersApi
import studio.inprogress.postertestapp.model.repository.IPosterRepository
import timber.log.Timber

class PosterRepositoryImpl(
    private val postersApi: IPostersApi,
    private val posterDao: PosterDao
): IPosterRepository {

    override suspend fun loadPosters(): List<PosterEntity> {
        Timber.d(Thread.currentThread().name)
        val posterEntityList = PosterEntity.from(postersApi.loadPosters())
        posterDao.insertPosters(posterEntityList)
        return posterEntityList
    }

    override suspend fun loadPostersFromCache(): List<PosterEntity> {
        return posterDao.getAll()
    }
}