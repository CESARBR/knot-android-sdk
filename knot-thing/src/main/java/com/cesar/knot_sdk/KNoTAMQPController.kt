package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTMessageRegister
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
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

}
