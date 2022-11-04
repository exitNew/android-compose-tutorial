fun main() {
	// without using null exception
	var favoriteActor: String = "Sandra Oh"
	println(favoriteActor) // Sandra Oh
	println(favoriteActor.length) // 9

	// using null ?
	var favoriteActor: String? = "Sandra Oh"
	println(favoriteActor) // Sandra Oh
	favoriteActor = null
	println(favoriteActor) // non nullable types
	
	// using null for properties ?.
	var favoriteActor: String? = null
	println(favoriteActor.length) // exeption error	
	println(favoriteActor?.length) // null

	// using non null assert !!. (always make sure before using that variable is not null)
	var favoriteActor: String? = null
	println(favoriteActor!!.length) // Null Exeption error 

	// using elvis operator / default value ?:
	var favoriteActor: String? = "Sandra Oh"
	val lengthName = favoriteActor?.length ?: 0
	println(lengthName) // 9
}
