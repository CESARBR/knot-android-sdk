package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTMessageRegister
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
import com.cesar.knot_sdk.knot_messages.KNoTMessageAuth

/**
 * This interface represents all operations that are available with the KNoT
 * protocol.
 * Classes that implement this interface can use any kind of technology to send
 * these messages (AMQP, WebSockets, etc), but should support these operations.
 */
interface KNoTMessager {

    /**
     * Register a KNoT Thing in the cloud.
     */
    fun register(knotThingRegister : KNoTMessageRegister)

    /**
     * Unregister a KNoT Thing in the cloud.
     */
    fun unregister(knotThingUnregister : KNoTMessageUnregister)

    /**
     * Authenticates a KNoT Thing in the cloud.
     */
    fun authenticate(knotMessageAuth: KNoTMessageAuth)

}
