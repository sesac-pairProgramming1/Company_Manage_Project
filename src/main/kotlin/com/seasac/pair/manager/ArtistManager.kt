package com.seasac.pair.manager

import com.seasac.pair.Entity.Artist
import com.seasac.pair.FeatureInterface
import com.seasac.pair.common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ArtistManager() : FeatureInterface {

    //파일이 없다.
    var artistList: MutableList<Artist> = deSerializationArtistFile()

    override fun <T> update(t: T) {
        var index = 0
        for (i in artistList.indices) {
            if (t == artistList[i].name) {
                index = i
                break
            }
        }
        artistList[index] = artistInput()
    }

    override fun showList() {
        deSerializationArtistFile()
        println(String.format("%39s","목록"))
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println("\t\t 가수 이름 \t\t\t│ \t\t\t 장르 \t\t\t │ \t\t\t 데뷔일 ")
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        artistList.forEach {
            println("\t\t ${it.name} \t\t\t\t \t\t\t ${it.genre} \t\t\t \t\t${it.debutDate}")
        }
    }

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
                            " ${artistList[index].genre} \t\t ${artistList[index].debutDate}"
                )
            }
        }
        if (!existFlag) {
            println("일치하는 결과가 없습니다.")
        }

    }

    override fun <T> delete(t: T) {
        val currentListCount = artistList.size
        for (i in artistList.indices) {
            if (t == artistList[i].name) {
                artistList.removeAt(i)
                break
            }
        }
        if (currentListCount - 1 == artistList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
        }
    }

    fun artistInput(): Artist {
        print("아티스트명 : ")
        val artistName = ConsoleReader.consoleLineScanner()
        print("장르 : ")
        val artistGenre = ConsoleReader.consoleLineScanner()
        print("데뷔날짜 : ")
        val artistDebutDate = ConsoleReader.consoleLineScanner()
        return Artist(artistName,artistGenre,artistDebutDate)
    }

    fun choiceArtistMenu(number: Int) {
        when (number) {
            1 -> {
                //입력
                enroll(artistInput())
                serializationArtistFile()
            }

            2 -> {
                print("아티스트명을 입력하세요 : ")
                val artistName = ConsoleReader.consoleLineScanner()
                search(artistName)
            }

            3 -> {
                print("수정할 아티스트명 : ")
                val artistName = ConsoleReader.consoleLineScanner()
                update(artistName)
                serializationArtistFile()
            }

            4 -> {
                print("삭제할 아티스트명 : ")
                val artistName = ConsoleReader.consoleLineScanner()
                delete(artistName)
                serializationArtistFile()
            }
        }
    }

    fun deSerializationArtistFile() = runBlocking {
        artistList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/artist.ser")).use {
                it.readObject() as MutableList<Artist>
            }
        }
        artistList
    }

    fun serializationArtistFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/artist.ser")).use {
                with(it) {
                    writeObject(artistList)
                    flush()
                }
            }
        }
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



