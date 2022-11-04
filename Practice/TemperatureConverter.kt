fun main() {
    // Fill in the code.
    val celciusToFahrenheit = printFinalTemperature(27.0, "Celcius", "Fahrenheit") {
        (it * 9/5) + 32
    }

    val kelvinToCelcius = printFinalTemperature(350.0, "Kelvin", "Celcius") {
        it - 273.15
    }

    val fahrenheitToKelvin = printFinalTemperature(10.0, "Fahrenheit", "Kelvin") {
        (it - 32) * 5/9 + 273.15
    }

//    celciusToFahrenheit
//    kelvinToCelcius
//    fahrenheitToKelvin
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
