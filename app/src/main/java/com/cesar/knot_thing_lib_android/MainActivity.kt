package com.cesar.knot_thing_lib_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesar.knot_sdk.KNoTAMQP
import com.cesar.knot_sdk.KNoTAMQPFactory
import com.cesar.knot_sdk.KNoTMessager
import com.cesar.knot_sdk.knot_messages.KNoTMessageRegister
import com.cesar.knot_sdk.knot_messages.KNoTMessageUnregister
import com.cesar.knot_sdk.knot_messages.KNoTMessageAuth
import com.cesar.knot_sdk.KNoTTypes.KNOT_TYPE_ID_SWITCH
import com.cesar.knot_sdk.KNoTTypes.KNOT_UNIT_NOT_APPLICABLE
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_BOOL
import com.cesar.knot_sdk.knot_data.KNoTSchema
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateSchema
import com.cesar.knot_sdk.knot_messages.KNoTMessageDataItem
import com.cesar.knot_sdk.knot_messages.KNoTMessageUpdateData
import kotlinx.android.synthetic.main.activity_main.register_button
import kotlinx.android.synthetic.main.activity_main.unregister_button
import kotlinx.android.synthetic.main.activity_main.authenticate_button
import kotlinx.android.synthetic.main.activity_main.schema_update_button
import kotlinx.android.synthetic.main.activity_main.data_publish_button
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    val HOSTNAME = "0.0.0.0"
    val PORT_NUMBER = 5672
    val USERNAME = "user"
    val PASSWORD = "pass"
    val THING_ID = "a74151d19de59cd3"
    val THING_NAME = "thing-name"
    val THING_TOKEN = "ejfhwekhrui234huirh23uf"
    val SENSOR_ID = 1
    val SENSOR_NAME = "updateSchemaTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val knotAMQP = KNoTAMQP(USERNAME, PASSWORD, HOSTNAME, PORT_NUMBER)
        lateinit var knotMessager : KNoTMessager
        KNoTAMQPFactory().create(knotAMQP)
        val knotThingRegister = KNoTMessageRegister(THING_ID, THING_NAME)
        val knotThingUnregister = KNoTMessageUnregister(THING_ID)
        val knotThingAuth = KNoTMessageAuth(THING_ID, THING_TOKEN)
        val knotThingSchema = mutableListOf(
            KNoTSchema(
                SENSOR_ID,
                KNOT_VALUE_TYPE_BOOL,
                KNOT_UNIT_NOT_APPLICABLE,
                KNOT_TYPE_ID_SWITCH,
                SENSOR_NAME
            )
        )

        val knotThingUpdateSchema = KNoTMessageUpdateSchema(THING_ID, knotThingSchema)


        val dataItem = object {
            val sensorId : Int = SENSOR_ID
            var value : Boolean = true
        }

        val knotData = mutableListOf(
            KNoTMessageDataItem(dataItem.sensorId, dataItem.value)
        )
        val knotThingUpdateData = KNoTMessageUpdateData(THING_ID, knotData)

        register_button.setOnClickListener {
            doAsync { knotMessager.register(knotThingRegister) }
        }

        unregister_button.setOnClickListener {
            doAsync { knotMessager.unregister(knotThingUnregister) }
        }

        authenticate_button.setOnClickListener {
            doAsync { knotMessager.authenticate(knotThingAuth) }
        }

        schema_update_button.setOnClickListener {
            doAsync { knotMessager.updateSchema(knotThingUpdateSchema) }
        }

        data_publish_button.setOnClickListener {
            doAsync { knotMessager.publishData(knotThingUpdateData) }
        }

    }
}
