package com.cesar.knot_sdk.knot_state_machine.states.base_classes.branching_state

import com.cesar.knot_sdk.knot_state_machine.states.base_classes.State
import com.cesar.knot_sdk.knot_state_machine.states.base_classes.branching_state.BranchingStateActions.Timeout

abstract class BranchingState : State() {

    abstract override fun enter()

    abstract override fun getErrorCode() : BranchingStateActions

    abstract override fun getNextState(message : String) : State

    /**
     * This method returns the appropriate action given the current state
     * conditions.
     */
    abstract fun getAppropriateAction() : BranchingStateActions

    override fun getNextAction(message : String) : BranchingStateActions {
        if(error != NO_ERROR_MESSAGE) return getErrorCode()
        if(message == TIMEOUT_MESSAGE) return Timeout
        return getAppropriateAction()
    }

}
