package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_data.KNoTValueInt
import org.junit.Assert
import org.junit.Test

class KNoTDataValueIntTest {

    val SENSOR_ID = 0
    val VALUE = 42
    val SMALLER_VALUE = 12
    val BIGGER_VALUE = 52
    val knotValue = KNoTValueInt(SENSOR_ID, VALUE)
    val smallerKNoTValue = KNoTValueInt(SENSOR_ID, SMALLER_VALUE)
    val biggerKNoTValue = KNoTValueInt(SENSOR_ID, BIGGER_VALUE)

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
