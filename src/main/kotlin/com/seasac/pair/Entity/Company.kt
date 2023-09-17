package com.seasac.pair.Entity

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
    val mainArtist : String?=null,
) : Serializable
