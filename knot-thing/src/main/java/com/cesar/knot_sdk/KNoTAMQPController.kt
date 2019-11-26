package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTMessageRegister
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
import com.cesar.knot_sdk.knot_messages.KNoTMessageAuth
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateSchema
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateData
import com.google.gson.Gson

/**
 * This class implements the KNoT-Protocol operations for AMQP.
 */
class KNoTAMQPController(val knotAMQP: KNoTAMQP) : KNoTMessager {

    override fun register(knotThingRegister : KNoTMessageRegister) {
        knotAMQP.publish(
            Gson().toJson(knotThingRegister),
            knotAMQP.EXCHANGE_NAME_CLOUD,
            knotAMQP.BINDING_KEY_REGISTER
        )
    }

    override fun unregister(knotThingUnregister : KNoTMessageUnregister) {
        knotAMQP.publish(
            Gson().toJson(knotThingUnregister),
            knotAMQP.EXCHANGE_NAME_CLOUD,
            knotAMQP.BINDING_KEY_UNREGISTER
        )
    }

    override fun authenticate(knotMessageAuth: KNoTMessageAuth) {
        knotAMQP.publish(
            Gson().toJson(knotMessageAuth),
            knotAMQP.EXCHANGE_NAME_CLOUD,
            knotAMQP.BINDING_KEY_AUTHENTICATE
        )
    }

    override fun updateSchema(knotMessageSchema: KNoTMessageUpdateSchema) {
        knotAMQP.publish(
            Gson().toJson(knotMessageSchema),
            knotAMQP.EXCHANGE_NAME_CLOUD,
            knotAMQP.BINDING_KEY_SCHEMA_UPDATE
        )
    }

    override fun publishData(knotMessageUpdateData: KNoTMessageUpdateData) {
        knotAMQP.publish(
            Gson().toJson(knotMessageUpdateData),
            knotAMQP.EXCHANGE_NAME_CLOUD,
            knotAMQP.BINDING_KEY_DATA_PUBLISH
        )
    }

}
