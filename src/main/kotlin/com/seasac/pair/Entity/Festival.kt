package com.seasac.pair.Entity

import java.io.Serializable

/**
 * Festival
 * @property name : PrimaryKey
 */
data class Festival(
    // PrimaryKey
    val name : Company,
    val title : String,
    val enrolledDate : String,
) : Serializable
