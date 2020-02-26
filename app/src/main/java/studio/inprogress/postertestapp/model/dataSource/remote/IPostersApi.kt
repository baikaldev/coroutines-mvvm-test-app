package studio.inprogress.postertestapp.model.dataSource.remote

import retrofit2.http.GET
import studio.inprogress.postertestapp.model.dataSource.remote.dto.PosterDto

interface IPostersApi {

    @GET("ar2code/apitest/master/movies.json")
    suspend fun loadPosters(): List<PosterDto>
}