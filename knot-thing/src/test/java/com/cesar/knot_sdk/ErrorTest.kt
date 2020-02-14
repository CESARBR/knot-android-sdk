package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTMessageAuthenticated
import com.cesar.knot_sdk.knot_messages.KNoTMessageRegistered
import com.cesar.knot_sdk.knot_messages.KNoTMessageSchemaResp
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
import com.cesar.knot_sdk.knot_state_machine.states.Error
import com.cesar.knot_sdk.knot_state_machine.states.base_classes.KNoTEvent.*
import org.junit.Test

class ErrorTest {

    private val randomThingId = "0123456789abcde"
    private val nullError = "null"
    private val error = "non null error"
    private val randomToken = "random token"
    private val state = Error()

    @Test
    fun timeoutEvent_isError() {
        val timeoutEvent = Timeout
        val nextState = state.getNextState(timeoutEvent)

        assert(nextState is Error)
    }

    @Test
    fun notReadyEvent_isError() {
        val notReadyEvent = NotReady
        val nextState = state.getNextState(notReadyEvent)

        assert(nextState is Error)
    }

    @Test
    fun readyEvent_withoutCredentials_isError() {
        val readyEvent = Ready
        val nextState = state.getNextState(readyEvent)

        assert(nextState is Error)
    }

    @Test
    fun readyEvent_withCredentials_isError() {
        val readyEvent = Ready
        val nextState = state.getNextState(readyEvent)

        assert(nextState is Error)
    }

    @Test
    fun authOkEvent_isError() {
        val authOkMessage = KNoTMessageAuthenticated(
                randomThingId,
                nullError
        )
        val authOkEvent = AuthOk(authOkMessage)
        val nextState = state.getNextState(authOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun authNotOkEvent_isError() {
        val authNotOkMessage = KNoTMessageAuthenticated(
                randomThingId,
                error
        )
        val authNotOkEvent = AuthNotOk(authNotOkMessage)
        val nextState = state.getNextState(authNotOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun regOkEvent_isError() {
        val regOkMessage = KNoTMessageRegistered(
                randomThingId,
                randomToken,
                nullError
        )
        val regOkEvent = RegOK(regOkMessage)
        val nextState = state.getNextState(regOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun regNotOkEvent_isError() {
        val regNotOkMessage = KNoTMessageRegistered(
                randomThingId,
                randomToken,
                error
        )
        val regNotOkEvent = RegNotOk(regNotOkMessage)
        val nextState = state.getNextState(regNotOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun schemaOkEvent_isError() {
        val schemaOkMessage = KNoTMessageSchemaResp(
                randomThingId,
                nullError
        )
        val schemaOkEvent = SchemaOk(schemaOkMessage)
        val nextState = state.getNextState(schemaOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun schemaNotOkEvent_isError() {
        val schemaOkMessage = KNoTMessageSchemaResp(
                randomThingId,
                error
        )
        val schemaOkEvent = SchemaNotOk(schemaOkMessage)
        val nextState = state.getNextState(schemaOkEvent)

        assert(nextState is Error)
    }

    @Test
    fun unregisterEvent_correctId_isError() {
        val unregisterMessage = KNoTMessageUnregister(
                randomThingId
        )
        val unregisterEvent = UnregisterEvent(unregisterMessage)
        val nextState = state.getNextState(unregisterEvent)

        assert(nextState is Error)
    }

}