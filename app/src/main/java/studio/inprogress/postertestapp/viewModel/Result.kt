package studio.inprogress.postertestapp.viewModel

sealed class Result<out T> {

    object Empty : Result<Nothing>()

    data class Success<out T : Any>(val data: T, val message: String? = null) : Result<T>()

    data class Error(val throwable: Throwable, val errorMessage: String? = null) : Result<Nothing>()
}

