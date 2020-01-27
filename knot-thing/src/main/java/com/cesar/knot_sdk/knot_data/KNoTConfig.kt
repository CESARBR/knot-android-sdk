package com.cesar.knot_sdk.knot_data

/**
 * This class holds the definitions of periodicity and of upper and lower limits for
 * the associated KNoTValue.
 *
 * @property lowerThreshold this is the inferior limit of acceptable KNoTValues
 * @property upperThreshold this is the superior limit of acceptable KNoTValues
 * @property timeSec this is a timeout for the update of the KNoTValue
 * @property changeFlag if true, the value changes there's a publish data
 * @property lowerThresholdFlag if true, will check the lowerThreshold after every
 * write/read operation for each data
 * @property upperThresholdFlag if true, will check the upperThreshold after every
 * write/read operation for each data
 * @property timeFlag if true, will enable a work to read from the KNoTData every
 * timeSec seconds
 */
data class KNoTConfig<T>(
    val lowerThreshold : KNoTValue<T>?,
    val upperThreshold : KNoTValue<T>?,
    val timeSec : Int = Int.MAX_VALUE,
    val changeFlag : Boolean = false,
    val lowerThresholdFlag : Boolean = false,
    val upperThresholdFlag : Boolean = false,
    val timeFlag : Boolean = false
)
