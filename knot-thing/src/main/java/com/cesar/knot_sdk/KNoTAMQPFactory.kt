package com.cesar.knot_sdk

import org.jetbrains.anko.doAsync

/**
 * The creation of the KNoTAMQP class depends on a series of complicated
 * operations, some of which involve network operations. This class is
 * responsible solely to ease the creation of KNoTAMQP instances.
 */
class KNoTAMQPFactory {

    /**
     * Configures the KNoT-AMQP instance.
     * @param knotAMQP a non-configured instance of the KNoTAMQP class.
     * @param callback a callback to return after the operations are done.
     */
    fun create(
        knotAMQP : KNoTAMQP
    ) {
        doAsync {
            knotAMQP.startConnection()
            knotAMQP.createExchange(knotAMQP.EXCHANGE_NAME_CLOUD)
            knotAMQP.createExchange(knotAMQP.EXCHANGE_NAME_FOG)
            knotAMQP.createQueue(knotAMQP.QUEUE_NAME_FOG_IN)
            knotAMQP.createQueue(knotAMQP.QUEUE_NAME_FOG_OUT)

            knotAMQP.bindQueue(
                knotAMQP.QUEUE_NAME_FOG_IN,
                knotAMQP.EXCHANGE_NAME_CLOUD,
                knotAMQP.BINDING_KEY_REGISTER
            )
        }
    }
}
