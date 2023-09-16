package com.seasac.pair.Entity

import common.Field
import java.io.Serializable

/**
 * Company
 * @property name : PrimaryKey
 */
data class Company(
    val name : String,
    val field : Field,
    val representation : String,
    val address : String,
    val groups : Artist,
) : Serializable

