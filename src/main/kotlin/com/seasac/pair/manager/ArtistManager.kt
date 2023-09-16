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
        var index=0
        for (i in artistList.indices) {
            if (t==artistList[i].name) {
                index=i
                break
            }
        }
        artistList[index]=artistData()
    }

    override fun <T> showList() { }

    override fun <T> enroll(t: T) {
        artistList.add(t as Artist)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        artistList.forEachIndexed { index, artist ->
            if (artist.name == t as String) {
                existFlag = true
                println(
                    "검색 결과 : ${artistList[index].name} \t\t" +
                            " ${artistList[index].genre} \t\t ${artistList[index].debutDate}")
            }
        }
        if (!existFlag) {
            println("일치하는 결과가 없습니다")
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
        if (currentListCount-1 == artistList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }
    fun choiceArtistMenu(number: Int) {
        when (number) {
            1 -> {

                enroll(artistData())
                serializationArtistFile()
            }

            2 -> {
                val artistName = ConsoleReader.consoleLineScanner()
                delete(artistName)
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
    fun artistData() : Artist{
        print("가수 이름 입력 : ")
        val artistName = ConsoleReader.consoleLineScanner()
        print("장르 입력 : ")
        val artistGenre = ConsoleReader.consoleLineScanner()
        print("데뷔날짜 입력 : ")
        val debutDate = ConsoleReader.consoleLineScanner()

        return Artist(artistName,artistGenre,debutDate)
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

