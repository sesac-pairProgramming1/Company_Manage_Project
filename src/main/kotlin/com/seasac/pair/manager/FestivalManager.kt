package com.seasac.pair.manager

import com.seasac.pair.Entity.Festival
import com.seasac.pair.FeatureInterface
import com.seasac.pair.common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FestivalManager : FeatureInterface {

    private var festivalList: MutableList<Festival> = deSerializationArtistFile()
    override fun <T> update(t: T) {

    }

    override fun <T> showList() {
        festivalList.forEach {
            println("${it.name} \t\t ${it.title} \t\t ${it.festivalDate}")
        }
    }

    override fun <T> enroll(t: T) {
        festivalList.add(t as Festival)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        var existIndex = 0
        festivalList.forEachIndexed { index, festival ->
            if (festival == t as Festival) {
                existFlag = true
                existIndex = index
            }
        }
        if (existFlag) {
            println(
                "검색 결과 : ${festivalList[existIndex].name} \t\t" +
                        " ${festivalList[existIndex].title} \t\t ${festivalList[existIndex].festivalDate}"
            )
        } else {
            println("일치하는 결과가 없습니다.")
            // Label 이용?
        }
    }

    override fun<T> delete(t: T){
        val currentListCount = festivalList.size
        for (i in festivalList.indices){
            if (t == festivalList[i].name){
                festivalList.removeAt(i)
                break
            }
        }
        if (currentListCount-1== festivalList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
        }
    }

    fun abcde(number: Int) {
        when (number) {
            1 -> {
                //입력
                print("회사입력 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                print("행사 제목 : ")
                val festivalTitle = ConsoleReader.consoleLineScanner()
                print("개최일 : ")
                val festivalDate = ConsoleReader.consoleLineScanner()
                enroll(Festival(companyName, festivalTitle, festivalDate))
                serializationArtistFile()
            }

            2 -> {
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationArtistFile()
            }
        }
    }
        fun deSerializationArtistFile() = runBlocking {
        festivalList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/festival.ser")).use {
               it.readObject() as MutableList<Festival>
            }
        }
       festivalList
    }

    fun serializationArtistFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/festival.ser")).use {
                with(it) {
                    writeObject(festivalList)
                    flush()
                }
            }
        }
        println(message)
    }
}
