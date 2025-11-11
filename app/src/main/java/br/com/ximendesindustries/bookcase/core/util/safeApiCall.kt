package br.com.ximendesindustries.bookcase.core.util

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T> {
    return try {
        val result = apiCall()
        Result.Success(result)
    } catch (e: Exception) {
        val errorMessage = when (e) {
            is java.net.UnknownHostException -> "Sem conexão com a internet"
            is java.net.SocketTimeoutException -> "Tempo de conexão esgotado"
            is retrofit2.HttpException -> "Erro HTTP ${e.code()}: ${e.message()}"
            else -> e.localizedMessage ?: "Erro desconhecido"
        }
        Result.Error(errorMessage, e)
    }
}
