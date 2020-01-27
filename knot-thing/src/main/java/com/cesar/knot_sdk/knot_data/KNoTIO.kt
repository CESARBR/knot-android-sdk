package com.cesar.knot_sdk.knot_data

interface KNoTIO <T> {

    fun reader() : T

    fun writer(knotValue : T)

}
