package com.seasac.pair.manager

import com.seasac.pair.Entity.Artist
import com.seasac.pair.FeatureInterface
import com.seasac.pair.common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.*

class ArtistManager() : FeatureInterface {

    val artistList: MutableList<Artist> = mutableListOf()

    override fun <T> update(t: T) {


    }

    override fun <T> showList() {
        artistList.forEach {
            println("${it.name} \t\t ${it.genre} \t\t ${it.debutDate}")
        }
    }

    override fun <T> enroll(t: T) {
        artistList.add(t as Artist)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        var existIndex = 0
        artistList.forEachIndexed { index, artist ->
            if (artist == t as Artist) {
                existFlag = true
                existIndex = index
            }
        }
        if (existFlag) {
            println(
                "검색 결과 : ${artistList[existIndex].name} \t\t" +
                        " ${artistList[existIndex].genre} \t\t ${artistList[existIndex].debutDate}"
            )
        } else {
            println("일치하는 결과가 없습니다.")
            // Label 이용?
        }
    }

    override fun <T> delete(t: T) {
        val currentListCount = artistList.size
        artistList.forEachIndexed { index, artist ->
            if (artist.name == (t as Artist).name) {
                artistList.removeAt(index)
            }
        }
        if (currentListCount - 1 == artistList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }

    companion object {

    }
}



