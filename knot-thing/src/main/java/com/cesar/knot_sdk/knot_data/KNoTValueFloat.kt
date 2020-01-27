package com.cesar.knot_sdk.knot_data

class KNoTValueFloat (
    override val sensorId : Int,
    var value : Float
) : KNoTValue<KNoTValueFloat> {

    override fun lesser(knotValue : KNoTValueFloat) = value < knotValue.value

    override fun greater(knotValue : KNoTValueFloat) = value > knotValue.value

    override fun reader() = this

    override fun writer(knotValue : KNoTValueFloat) {
        value = knotValue.value
    }

    override fun toString() = "SensorID: $sensorId\nValue: $value"
    override fun getValues() = value
}
