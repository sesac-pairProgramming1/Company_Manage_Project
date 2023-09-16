package com.seasac.pair.Entity

import com.seasac.pair.common.Genre
import java.io.Serializable

/**
 * Artist
 * @property name : PrimaryKey
 */

data class Artist(
    // PrimaryKey
    val name : String,
    val genre : Genre? =null,
    val debutDate : String? = null,
) : Serializable

