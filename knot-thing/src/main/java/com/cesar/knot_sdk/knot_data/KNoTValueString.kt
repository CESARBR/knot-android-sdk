package com.cesar.knot_sdk.knot_data

class KNoTValueString (
    override val sensorId : Int,
    var value : String
) : KNoTValue<KNoTValueString> {

    override fun lesser(knotValue : KNoTValueString) =
        value.length < knotValue.value.length

    override fun greater(knotValue : KNoTValueString) =
        value.length > knotValue.value.length

    override fun toString() = "SensorID: " + sensorId + "\nValue: " + value

    override fun reader() = this

    override fun writer(knotValue : KNoTValueString) {
        value = knotValue.value
    }

    override fun getValues() = value
}
