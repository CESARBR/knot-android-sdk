package com.cesar.knot_sdk.knot_data

import com.cesar.knot_sdk.knot_messages.KNoTMessageDataItem

/**
 * This class is an abstraction over a sensor. The schema defines metadata about
 * the sensor, the config defines how often the sensor updates and the value
 * defines the current value of the sensor.
 * @property schema the schema containing the sensor metadata
 * @property config the config with the flags/limits of the sensor value
 * @property value the current value of the sensor
 */
class KNoTData <T> (
    val schema : KNoTSchema,
    private val config : KNoTConfig<T>,
    val value : KNoTValue<T>
) {

    private fun checkUpperBound(currValue : T) = config.upperThreshold!!.lesser(currValue)

    private fun checkLowerBound(currValue : T) = config.lowerThreshold!!.greater(currValue)

    //TODO: This method does not work as expected since it's comparing objects
    //      instead of the value itself.
    private fun checkValueChange(currValue : T) = value.equals(currValue)

    fun toKNoTMessageData() =  KNoTMessageDataItem(
        getSensorId(),
        value.getValues()
    )

    fun getSensorId() = schema.sensorId

    fun getSensorType() = schema.valueType

    fun updateValue(knotValue : T) = value.writer(knotValue)

    fun checkValue() : Boolean {
        val currValue = value.reader()
        if(config.lowerThresholdFlag) {
            if(checkLowerBound(currValue)) return true
        }
        else if(config.upperThresholdFlag) {
            if(checkUpperBound(currValue)) return true
        }
        else if(config.changeFlag) {
            if(checkValueChange(currValue)) return true
        }
        return false
    }

}
