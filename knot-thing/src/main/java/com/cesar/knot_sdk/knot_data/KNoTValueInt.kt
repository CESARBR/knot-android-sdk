package com.cesar.knot_sdk.knot_data

class KNoTValueInt (
    override val sensorId : Int,
    var value : Int
) : KNoTValue<KNoTValueInt> {

    override fun lesser(knotValue : KNoTValueInt) = value < knotValue.value

    override fun greater(knotValue : KNoTValueInt) = value > knotValue.value

    override fun toString() = "SensorID:  $sensorId \nValue: $value"

    override fun reader() = this

    override fun writer(knotValue : KNoTValueInt) {
        value = knotValue.value
    }

    override fun getValues() = value
}
