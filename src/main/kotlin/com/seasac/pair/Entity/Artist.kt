package com.seasac.pair.Entity

import common.Genre
import java.io.Serializable

/**
 * Artist
 * @property name : PrimaryKey
 */
data class Artist(
    // PrimaryKey
    val name : String,
    val genre : Genre,
    val debutDate : String,
) : Serializable
