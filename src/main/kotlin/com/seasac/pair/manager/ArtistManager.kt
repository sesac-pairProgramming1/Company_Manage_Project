package com.seasac.pair.manager

import com.seasac.pair.Entity.Artist
import com.seasac.pair.FeatureInterface
import common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ArtistManager() : FeatureInterface {

    private var artistList: MutableList<Artist> = mutableListOf()
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
        for(i in artistList.indices) {
            if (artistList[i].name==t as String) {
                artistList.removeAt(i)
                break
            }
        }
        if (currentListCount == artistList.size - 1) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }
    fun choiceArtistMenu(number: Int) {
        when (number) {
            1 -> {
                print("가수 이름 입력 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                print("장르 입력 : ")
                val artistGenre = ConsoleReader.consoleLineScanner()
                print("데뷔날짜 입력 : ")
                val festivalDate = ConsoleReader.consoleLineScanner()
                enroll(Artist(companyName, artistGenre, festivalDate))
                serializationArtistFile()
            }

            2 -> {
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationArtistFile()
            }
            3-> {
                print("수정할 가수의 이름을 입력 : ")
                val existName=ConsoleReader.consoleLineScanner()

            }
            4 -> {

            }
        }
    }
    fun deSerializationArtistFile() = runBlocking {
        artistList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/festival.ser")).use {
                it.readObject() as MutableList<Artist>
            }
        }
        artistList
    }

    fun serializationArtistFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/festival.ser")).use {
                with(it) {
                    writeObject(artistList)
                    flush()
                }
            }
        }
        println(message)
    }


    companion object {
        private var INSTANCE : ArtistManager? =null

        fun initialize() {
            if(INSTANCE==null) {
                INSTANCE= ArtistManager()
            } else {
                println("이미 초기화 되었습니다.")
            }
        }
    }
}

