package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that deserialize the response of the authentication message.
 * @property id the Thing unique identifier
 * @property error an error message if the request fails
 */
data class KNoTMessageRemoved(
    val id : String,
    val error: String
)
