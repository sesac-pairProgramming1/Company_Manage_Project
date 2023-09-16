package com.seasac.pair.Entity

import java.io.Serializable

/**
 * Artist
 * @property name : PrimaryKey
 */

data class Artist(
    // PrimaryKey
    val name : String,
    val genre : String? =null,
    val debutDate : String? = null,
) : Serializable

