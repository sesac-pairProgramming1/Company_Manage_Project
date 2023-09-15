package com.seasac.pair.manager

import Entity.Artist
import com.seasac.pair.FeatureInterface

fun ArtistManager(){

    val artistList : MutableList<Artist> = mutableListOf()

    class functions():FeatureInterface{
        override fun <T> update(t: T) {

        }


        override fun <T> showList(): MutableList<T> {
            return mutableListOf()
        }

        override fun <T> enroll(t: T) {
            artistList.add(t as Artist)
        }

        override fun <T> search(t: T): T {

        }

        override fun delete() {
            TODO("Not yet implemented")
        }

    }



}