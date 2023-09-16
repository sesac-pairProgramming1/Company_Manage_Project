package com.seasac.pair.Entity

import com.seasac.pair.common.Field
import java.io.Serializable

/**
 * Company
 * @property name : PrimaryKey
 */
data class Company(
    val name : String,
    val field : String?=null,
    val representation : String?=null,
    val address : String?=null,
    val groups : String?=null,
) : Serializable
