package com.seasac.pair.Entity

import java.io.Serializable

/**
 * Festival
 * @property name : PrimaryKey
 */
data class Festival(
    // PrimaryKey
    val name : String,
    val title : String?=null,
    val festivalDate : String?=null,
) : Serializable
