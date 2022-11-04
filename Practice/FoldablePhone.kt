open class Phone(
    var isScreenLightOn: Boolean = false
) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if(isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(
    screenLightOn: Boolean
): Phone(
    isScreenLightOn = screenLightOn
) {
    protected var isPhoneFolded = true

    override fun switchOn() {
        super.switchOn()
        isPhoneFolded = false
    }

    override fun switchOff() {
        super.switchOff()
        isPhoneFolded = true
    }

    fun checkIfPhoneIsFolded(isFolded: Boolean) {
        if (isFolded) {
            switchOff()
            super.checkPhoneScreenLight()
            println("Phone is folded")
        } else {
            switchOn()
            super.checkPhoneScreenLight()
            println("Phone is unfolded")
        }
    }
}

fun main() {
    val phone = FoldablePhone(true)
    phone.checkIfPhoneIsFolded(true)
    phone.checkIfPhoneIsFolded(false)
}