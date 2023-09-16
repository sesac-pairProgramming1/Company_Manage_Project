package com.seasac.pair.Entity

import java.io.Serializable

/**
 * Company
 * @property name : PrimaryKey
 */
data class Company(
    val name : String,
    val field : String,
    val representation : String,
    val address : String,
    val group : String,
) : Serializable

