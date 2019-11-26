package com.cesar.knot_thing_lib_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesar.knot_sdk.KNoTAMQP
import com.cesar.knot_sdk.KNoTAMQPFactory
import com.cesar.knot_sdk.KNoTMessager
import com.cesar.knot_sdk.knot_messages.KNoTMessageRegister
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
import kotlinx.android.synthetic.main.activity_main.register_button
import kotlinx.android.synthetic.main.activity_main.unregister_button
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    val HOSTNAME = "0.0.0.0"
    val PORT_NUMBER = 5672
    val USERNAME = "user"
    val PASSWORD = "pass"
    val THING_ID = "a74151d19de59cd3"
    val THING_NAME = "thing-name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val knotAMQP = KNoTAMQP(USERNAME, PASSWORD, HOSTNAME, PORT_NUMBER)
        lateinit var knotMessager : KNoTMessager
        KNoTAMQPFactory().create(knotAMQP)
        val knotThingRegister = KNoTMessageRegister(THING_ID, THING_NAME)
        val knotThingUnregister = KNoTMessageUnregister(THING_ID)

        register_button.setOnClickListener {
            doAsync { knotMessager.register(knotThingRegister) }
        }

        unregister_button.setOnClickListener {
            doAsync { knotMessager.unregister(knotThingUnregister) }
        }
    }
}
