package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_data.KNoTValueFloat
import org.junit.Test

class KNoTDataValueFloatTest {

    val SENSOR_ID = 0
    val VALUE  : Float = 42F
    val SMALLER_VALUE = 12F
    val BIGGER_VALUE = 52F
    val knotValue = KNoTValueFloat(SENSOR_ID, VALUE)
    val smallerKNoTValue = KNoTValueFloat(SENSOR_ID, SMALLER_VALUE)
    val biggerKNoTValue = KNoTValueFloat(SENSOR_ID, BIGGER_VALUE)

    @Test
    fun lesser_isCorrect() {
        assert(smallerKNoTValue.lesser(knotValue))
    }

    @Test
    fun lesser_isFalse() {
        assert(!knotValue.lesser(smallerKNoTValue))
    }

    @Test
    fun greater_isCorrect() {
        assert(biggerKNoTValue.greater(knotValue))
    }

    @Test
    fun greater_isFalse() {
        assert(!knotValue.greater(biggerKNoTValue))
    }

    @Test
    fun equal_isFalse() {
        assert(!knotValue.greater(knotValue))
        assert(!knotValue.lesser(knotValue))
    }

}
