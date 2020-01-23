package com.cesar.knot_sdk

import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_NULL
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_BOOL
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_FLOAT
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_MAX
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_RAW
import com.cesar.knot_sdk.knot_messages.KNoTMessageAuthenticated
import com.cesar.knot_sdk.knot_messages.KNoTMessageDataItem
import com.cesar.knot_sdk.knot_messages.KNoTMessageRequestData
import com.cesar.knot_sdk.knot_messages.KNoTMessageSchemaResp
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateData
import com.google.gson.JsonParser

/**
 * This class parses southbound traffic json requests in usable kotlin objects.
 */
class KNoTMessageParser {

    private val sensorHash = mutableMapOf<Int, Int>()
    private val VALUE = "value"
    private val DATA = "data"
    private val ID = "id"
    private val SENSOR_ID = "sensorId"
    private val SENSOR_IDS = "sensorIds"
    private val TOKEN = "token"
    private val ERROR = "error"
    private val ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE =
            "The KNoTMessageParser is only prepared to handle " +
            "KNOT_VALUE_TYPE_INT, KNOT_VALUE_TYPE_FLOAT, " +
            "KNOT_VALUE_TYPE_BOOL, " + "KNOT_VALUE_TYPE_RAW, KNOT_VALUE_NULL."

    private fun getSensorType(sensorId : Int) : Int {
        val sensorType = sensorHash[sensorId]

        if (sensorType == null) return KNOT_VALUE_NULL

        return sensorType
    }

    fun addSensor(key : Int, value : Int) {
        if (value >= KNOT_VALUE_TYPE_MAX) {
            throw IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE)
        }
        sensorHash[key] = value
    }

    fun parseDataUpdate(knotData : String) : KNoTMessageUpdateData {
        val receivedJsonObject = JsonParser.parseString(knotData).asJsonObject
        val id = receivedJsonObject.get(ID).asString
        val dataJasonArray = receivedJsonObject.getAsJsonArray(DATA)
        val data = mutableListOf<KNoTMessageDataItem>()
        var type : Int
        var sensorId : Int
        dataJasonArray.forEach {
            sensorId = it.asJsonObject.get(SENSOR_ID).asInt
            type = getSensorType(sensorId)
            when(type) {
                KNOT_VALUE_TYPE_INT -> data.add(KNoTMessageDataItem(
                    sensorId,
                    it.asJsonObject.get(VALUE).asInt)
                )

                KNOT_VALUE_TYPE_FLOAT -> data.add(KNoTMessageDataItem(
                    sensorId,
                    it.asJsonObject.get(VALUE).asFloat)
                )

                KNOT_VALUE_TYPE_BOOL -> data.add(KNoTMessageDataItem(
                    sensorId,
                    it.asJsonObject.get(VALUE).asBoolean)
                )

                KNOT_VALUE_TYPE_RAW -> data.add(KNoTMessageDataItem(
                    sensorId,
                    it.asJsonObject.get(VALUE).asString)
                )
            }
        }

        return KNoTMessageUpdateData(id, data)
    }

    fun parseDataRequest(knotData : String) : KNoTMessageRequestData {
        val receivedJsonObject = JsonParser.parseString(knotData).asJsonObject
        val id = receivedJsonObject.get(ID).asString
        val sensorIdsJsonArray = receivedJsonObject.getAsJsonArray(SENSOR_IDS)
        val sensorIds = mutableListOf<Int>()

        sensorIdsJsonArray?.forEach {
            sensorIds.add(it.asInt)
        }

        return KNoTMessageRequestData(id, sensorIds)
    }

    fun parseSchemaStatus(knotSchema : String) : KNoTMessageSchemaResp {
        val receivedJson = JsonParser.parseString(knotSchema).asJsonObject
        val id = receivedJson.get(ID).asString
        val error = receivedJson.get(ERROR).toString()

        return KNoTMessageSchemaResp(id, error)
    }

    fun parseAuthStatus(knotAuth : String) : KNoTMessageAuthenticated {
        val receivedJson = JsonParser.parseString(knotAuth).asJsonObject
        val id = receivedJson.get(ID).asString
        val error = receivedJson.get(ERROR).toString()

        return KNoTMessageAuthenticated(id, error)
    }
}
