import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Super Class
open class SmartDevice(
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
}


// IS - A
class SmartTvDevice(
	deviceName: String,
	deviceCategory: String
): SmartDevice( // pass value to superclass
	name = deviceName, 
	category = deviceCategory
) {

	// using standard getset
	//private var speakerVolume = 2
	//	set(value) {
	//		if(value in 1..100) {
	//			field = value
	//		}
	//	}

	// using delegate by
	private var speakerVolume by RangeRegulator(initialValue = 0, minValue = 1, maxValue = 100)	

	// using standard get set
	//private var channelNumber = 1
	//	set(value) {
	//		if(value in 0..200) {
	//			field = value
	//		}
	//	}

	// using delagete by
	private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)	

	override val deviceType: String = "Smart TV" // override is using superclass properties(open) with subclass properties(override)

	override fun turnOn() {
		//deviceStatus = "On"
		super.turnOn() // Using method from superclass
		println("$name is turned On. Speaker volume is set to $speakerVolume and channel number is " + " set to $channelNumber")
	}

	override fun turnOff() {
		//deviceStatus = "Off"
		super.turnOff()
		println("$name is turned off") 
	}	

	fun increaseSpeakerVolume() {
		speakerVolume++;
		println("Speaker Volume Increased $speakerVolume");
	}	

	fun nextChannel() {
		channelNumber++
		println("Channel changed to $channelNumber")
	}
}

// IS - A
class SmartLightDevice(
	deviceName: String,
	deviceCategory: String
): SmartDevice(
	name = deviceName,
	category = deviceCategory
) {
	
	// using standard get set
	//private var brightnessLevel = 0
	//	set(value) {
	//		if(value in 1..100) {
	//			field = value
	//		}
	//	}

	// using delegate by
	private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

	override val deviceType: String = "Smart Light"
	
	override fun turnOn() {
		//deviceStatus = "On"
		super.turnOn()
		brightnessLevel = 2
		println("$name turned on. The brightness level is $brightnessLevel")
	}

	override fun turnOff() {
		//deviceStatus = "Off"
		super.turnOff()
		brightnessLevel = 0
		println("$name turned off. The brightness level is $brightnessLevel")
	}

	fun increaseBrightness() {
		brightnessLevel++;
		println("Brightness level now $brightnessLevel")
	}
}

// HAS - A 
class SmartHome(
	val smartTvDevice: SmartTvDevice,
	val smartLightDevice: SmartLightDevice
) {
	var deviceTurnOnCount = 0
		private set
	
	fun turnOnTv() {
		deviceTurnOnCount++;
		smartTvDevice.turnOn();
	}

	fun turnOffTv() {
		deviceTurnOnCount--;
		smartTvDevice.turnOff();
	}

	fun increaseTvVolume() {
		smartTvDevice.increaseSpeakerVolume()
	}

	fun changeTvChannelToNext() {
		smartTvDevice.nextChannel()
	}

	fun turnOnLight() {
		deviceTurnOnCount++
		smartLightDevice.turnOn()
	}

	fun turnOffLight() {
		deviceTurnOnCount--
		smartLightDevice.turnOff()
	}

	fun increaseLightBrightness() {
		smartLightDevice.increaseBrightness()
	}

	fun turnOffAllDevices() {
		turnOffTv()
		turnOffLight()
	}
}

// delegate
class RangeRegulator(
	initialValue: Int,
	private val minValue: Int,
	private val maxValue: Int
): ReadWriteProperty<Any?, Int> { // readwriteproperty for var variable // readonlyproperty for val variable
	
	private var fieldData = initialValue
	
	override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
		return fieldData;
	}
	
	override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
		if(value in minValue..maxValue) {
			fieldData = value
		}
	}
}


fun main() {
	//var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
	//smartDevice.turnOn()
	//smartDevice = SmartLightDevice("Google Light", "Utility")
	//smartDevice.turnOn()
	
	val smartHome = SmartHome(
		SmartTvDevice(deviceName = "Android TV", deviceCategory = "Entertainment"),
		SmartLightDevice(deviceName = "Google Light", deviceCategory = "Utility")
	)

	smartHome.turnOnTv()
	smartHome.turnOnLight()
	println("Total devices currently turned on: ${smartHome.deviceTurnOnCount}")
	println()
	
	smartHome.increaseTvVolume()
	smartHome.changeTvChannelToNext()
	smartHome.increaseLightBrightness()
	println()

	smartHome.turnOffAllDevices()
	println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")	


}

// Overridden use open in super class to be override, use override in derived class
// Default all can be access public, protected just inherited class, private is only class, internal its only module
// Use super for access super class variable or function
// Use class Name constructor(): OtherClass() or simplified (:) to pass to superclass 
