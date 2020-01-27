package com.cesar.knot_sdk

import com.cesar.knot_sdk.KNoTTypes.KNOT_TYPE_ID_NONE
import com.cesar.knot_sdk.KNoTTypes.KNOT_UNIT_NOT_APPLICABLE
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.cesar.knot_sdk.knot_data.KNoTConfig
import com.cesar.knot_sdk.knot_data.KNoTData
import com.cesar.knot_sdk.knot_data.KNoTSchema
import com.cesar.knot_sdk.knot_data.KNoTValueInt
import org.junit.Test

class KNoTDataTest {

    val SENSOR_ID = 0
    val INT_STANDARD_VALUE = 0
    val NAME = "Irrelevant name"
    val lowerLimit = KNoTValueInt(SENSOR_ID, 1)
    val upperLimit = KNoTValueInt(SENSOR_ID, 10)
    val TIME_SEC = 12

    val knotValue = KNoTValueInt(
        SENSOR_ID,
        INT_STANDARD_VALUE
    )

    val knotSchema = KNoTSchema(
        SENSOR_ID,
        KNOT_VALUE_TYPE_INT,
        KNOT_TYPE_ID_NONE,
        KNOT_UNIT_NOT_APPLICABLE,
        NAME
    )

    val irrelevantConfig = KNoTConfig(
        lowerLimit,
        upperLimit,
        TIME_SEC
    )


    @Test
    fun lowerThreshold_isFalse() {
        val knotConfig = KNoTConfig(
            lowerLimit,
            upperLimit,
            TIME_SEC
        )

        val knotData = KNoTData(
            knotSchema,
            knotConfig,
            knotValue
        )

        val res = knotData.checkValue()
        assert(!res)
    }

    @Test
    fun lowerThreshold_isCorrect() {
        val knotConfig = KNoTConfig(
            lowerLimit,
            upperLimit,
            TIME_SEC,
            true
        )

        val knotData = KNoTData(
            knotSchema,
            knotConfig,
            knotValue
        )

        val res = knotData.checkValue()
        assert(res)
    }

    @Test
    fun upperThreshold_isFalse() {
        val knotConfig = KNoTConfig(
            lowerLimit,
            upperLimit,
            TIME_SEC
        )

        val knotData = KNoTData(
            knotSchema,
            knotConfig,
            knotValue
        )

        val res = knotData.checkValue()
        assert(!res)
    }

    @Test
    fun upperThreshold_isCorrect() {
        val knotConfig = KNoTConfig(
            lowerLimit,
            upperLimit,
            TIME_SEC,
            false,
            true
        )

        val knotData = KNoTData(
            knotSchema,
            knotConfig,
            knotValue
        )

        val res = knotData.checkValue()
        assert(res)
    }

}
