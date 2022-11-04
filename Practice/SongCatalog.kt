class SongCatalog(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {
    var isPopular: Boolean = true
        protected set

//    val isPopular: Boolean
//        get() = playCount >= 1000


    fun checkPopluarity(): Boolean {
        return playCount >= 1000
    }

    fun printSong() {
        println("Title: $title, Artist: $artist, Published: $yearPublished")
    }
}

fun main() {
    val songSashimi = SongCatalog("Sashimi", "DJ Sashimi", 2091, 2_000)
    songSashimi.printSong()
//    println(songSashimi.isPopular)
    println("This song is ${if(songSashimi.checkPopluarity()) "popular" else "unpopular"}")
    println()

    val songYakisoba = SongCatalog("Yaki-Soba", "DJ Soba Ni", 2088, 998)
    songYakisoba.printSong()
//    println(songYakisoba.isPopular)
    println("This song is ${if(songYakisoba.checkPopluarity()) "popular" else "unpopular"}")
    println()



}