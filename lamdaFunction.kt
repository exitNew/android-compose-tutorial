fun main() {
	// 1
	//val trickFunction = trick()
	//trickFunction(); // error

	// 2
	//val trickFunction = ::trick // tell compiler to search invocation (Function reference operator ::)
	//trickFunction() // No tricks!

	// 3 and 4
	//val trickFunction = trick
	//trickFunction() // No tricks
	//treat() // Have a treat

	// 5
	val coins: (Int) -> String = {
//		quantity -> "$quantity quarters"
		"$it quarters" //
	}

	println(coins(10))

	val cupcake: (Int) -> String = { // if there is parameter name kotlin will name it as "it"
		"Have a cupcake!"
	}

//	val treatFunction = trickOrTreat(false, coins) // pass coin function in trickOrTreat function if they called in function then coins function is triggered
//	val treatFunction = trickOrTreat(false, {// using lambda function in parameter
//		"$it quarters"
//	})
	val treatFunction = trickOrTreat(false) {// lambda trailing syntax (high order function)
		"$it quarters"
	}

	val trickFunction = trickOrTreat(true, cupcake)
//	val trickFunction = trickOrTreat(true, null)

	repeat(4) { // repeat is also trailing lambda syntax
		treatFunction()
	}
	trickFunction()
}

// 1 and 2 problem
//fun trick() { 
//	println("No tricks!")
//}


// 3 and 5 problem lambda function
val trick = { // lambda expression
	println("No tricks")
}

// 4 and 5 problem parameter and return
//val treat: (Int) -> String = { // if there is one parameter
val treat: () -> Unit = {
	println("Have a treat")
}

// 5

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit { // like void no return if use Unit
	if(isTrick) {
		if(extraTreat != null) {
			println(extraTreat(4))
		}
		return trick
	} else {
		if(extraTreat != null) {
			println(extraTreat(5))
		}
		return treat
	}
}


