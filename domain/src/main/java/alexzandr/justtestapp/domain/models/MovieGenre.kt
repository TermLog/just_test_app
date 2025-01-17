package alexzandr.justtestapp.domain.models

data class MovieGenre(
    val id: Int,
    val name: String?
){
    companion object {
        val EMPTY by lazy {
            MovieGenre(id = -1, name = null)
        }
    }
}