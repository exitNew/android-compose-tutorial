fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        // Fill in code
        println("Name: $name")
        println("Age: $age")
        println("Likes to play $hobby. ${
            if(referrer != null) {
                "Does have referrer named ${referrer.name}, who likes to play ${referrer.hobby}"
            } else {
                "Doesnt have any referrer"
            }
        }")
    }
}
