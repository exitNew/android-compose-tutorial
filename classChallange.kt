import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Super Class
public open class SmartDevice(
	val name: String,
	val category: String
) {
	var deviceStatus = "online"
		protected set
	
	open val deviceType: String = "Unknown"	

	open fun turnOn() {
		deviceStatus = "On"
	}

	open fun turnOff() {
		deviceStatus = "Off"
	} 

	open fun printDeviceInfo() {
		println("Device name: $name, category: $category, type: $deviceType")
	}
}


class SmartTvDevice(
	deviceName: String,
	deviceCategory: String
): SmartDevice(
	name = deviceName, 
	category = deviceCategory
) {

	private var speakerVolume by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 100)	

	private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)	

	override val deviceType: String = "Smart TV"

	override fun turnOn() {
		super.turnOn()
		println("$name is turned On. Speaker volume is set to $speakerVolume and channel number is " + " set to $channelNumber")
	}

	override fun turnOff() {
//		deviceStatus = "Off"
		super.turnOff()
		println("$name is turned off") 
	}	

	fun increaseSpeakerVolume() {
		speakerVolume++
		println("Speaker Volume Increased $speakerVolume")
	}	

	fun decreaseSpeakerVolume() {
		speakerVolume--
		println("Speaker Volume Decreased $speakerVolume")
	}

	fun nextChannel() {
		channelNumber++
		println("Channel changed to $channelNumber")
	}

	fun previousChannel() {
		channelNumber--
		println("Channel changed to $channelNumber")
	}
}

class SmartLightDevice(
	deviceName: String,
	deviceCategory: String
): SmartDevice(
	name = deviceName,
	category = deviceCategory
) {
	
	private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

	override val deviceType: String = "Smart Light"
	
	override fun turnOn() {
		super.turnOn()
		brightnessLevel = 2
		println("$name turned on. The brightness level is $brightnessLevel")
	}

	override fun turnOff() {
		super.turnOff()
		brightnessLevel = 0
		println("$name turned off. The brightness level is $brightnessLevel")
	}

	fun increaseBrightness() {
		brightnessLevel++
		println("Brightness level increased $brightnessLevel")
	}

	fun decreaseBrightness() {
		brightnessLevel--
		println("Brightness level decreased $brightnessLevel")
	}
}

class SmartHome(
	val smartTvDevice: SmartTvDevice,
	val smartLightDevice: SmartLightDevice
) {
	var deviceTurnOnCount = 0
		private set
	
	fun turnOnTv() {
		deviceTurnOnCount++
		smartTvDevice.turnOn()
	}

	fun turnOffTv() {
		deviceTurnOnCount--
		smartTvDevice.turnOff()
	}

	fun printTvDeviceInfo() {
		smartTvDevice.printDeviceInfo()
	}

	fun increaseTvVolume() {
		smartTvDevice.increaseSpeakerVolume()
	}

	fun decreaseVolume() {
		smartTvDevice.decreaseSpeakerVolume()
	}

	fun changeTvChannelToNext() {
		smartTvDevice.nextChannel()
	}

	fun changeTvChannelToPrevious() {
		smartTvDevice.previousChannel()
	}

	fun turnOnLight() {
		deviceTurnOnCount++
		smartLightDevice.turnOn()
	}

	fun turnOffLight() {
		deviceTurnOnCount--
		smartLightDevice.turnOff()
	}

	fun printLightDeviceInfo() {
		smartLightDevice.printDeviceInfo()
	}

	fun increaseLightBrightness() {
		smartLightDevice.increaseBrightness()
	}

	fun decreaseLightBrightness() {
		smartLightDevice.decreaseBrightness()
	}

	fun turnOffAllDevices() {
		turnOffTv()
		turnOffLight()
	}
}

class RangeRegulator(
	initialValue: Int,
	private val minValue: Int,
	private val maxValue: Int
): ReadWriteProperty<Any?, Int> {
	
	private var fieldData = initialValue
	
	override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
		return fieldData
	}
	
	override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
		if(value in minValue..maxValue) {
			fieldData = value
		}
	}
}


fun main() {
	val smartHome = SmartHome(
		SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
		SmartLightDevice(deviceName = "Google Light", deviceCategory = "Utility")
	)

	smartHome.printTvDeviceInfo()
	smartHome.printLightDeviceInfo()

	smartHome.turnOnTv()
	smartHome.turnOnLight()
	println("Total devices currently turned on: ${smartHome.deviceTurnOnCount}")
	println()
	
	smartHome.increaseTvVolume()
	smartHome.decreaseVolume()
	smartHome.changeTvChannelToNext()
	smartHome.changeTvChannelToPrevious()
	smartHome.increaseLightBrightness()
	smartHome.decreaseLightBrightness()
	println()

	smartHome.turnOffAllDevices()
	println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")	


}
