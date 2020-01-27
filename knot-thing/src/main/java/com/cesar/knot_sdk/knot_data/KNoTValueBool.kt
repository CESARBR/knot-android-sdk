package com.cesar.knot_sdk.knot_data

class KNoTValueBool (
    override val sensorId : Int,
    var value : Boolean
) : KNoTValue<KNoTValueBool> {

    override fun lesser(knotValue : KNoTValueBool) =
        this.value && knotValue.value

    override fun greater(knotValue : KNoTValueBool) =
        this.value || knotValue.value

    override fun toString() = "SensorID: $sensorId\nValue: $value"

    override fun reader() = this

    override fun writer(knotValue : KNoTValueBool) {
        value = knotValue.value
    }

    override fun getValues() = value
}
