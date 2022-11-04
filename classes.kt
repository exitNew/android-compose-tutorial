class SmartDevice(
	val name: String,
	val category: String
) {
	var deviceStatus = "Online"

	constructor(
		name: String,
		category: String,
		statusCode: Int
	): this(name, category) {
		deviceStatus = when(statusCode) {
			0 -> "Offline",
			1 -> "Online",
			else -> "Unknown Status"
		}
	}

	fun turnOn() {
		println("Device is Turning On");
	}

	fun turnOff() {
		println("Device is Turning Off");
	}
}

fun main() {
	val smartTVDevices = SmartDevice("Android TV", "Entertainment");
	smartTVDevices.turnOn();
}
