package com.cesar.knot_sdk.works

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateData
import com.cesar.knot_sdk.knot_state_machine.KNoTStateMachine.knotDataManager
import com.cesar.knot_sdk.knot_state_machine.KNoTStateMachine.knotMessager
import java.lang.Exception

class PeriodicPublishWork (val context : Context, val workerParams : WorkerParameters)
    : Worker(context, workerParams) {

    override fun doWork() = try {
        val currentDataValues = knotDataManager.getKNoTDataValues()
        val updateDataRequest = KNoTMessageUpdateData("1231", currentDataValues)
        knotMessager.publishData(updateDataRequest)
        Result.success()
    } catch (e : Exception) {
        Log.d("DEV-LOG", "PeriodicPublishWork exception: $e")
        Result.failure()
    }


    override fun onStopped() {
        super.onStopped()
        Log.d("DEV-LOG", "PeridoicPublishWork onStopped called,")
    }
}
