package com.seasac.pair.Entity

import common.Genre

/**
 * Artist
 * @property name : PrimaryKey
 */
data class Artist(
    // PrimaryKey
    val name : String,
    val genre : Genre,
    val debutDate : String,
)
