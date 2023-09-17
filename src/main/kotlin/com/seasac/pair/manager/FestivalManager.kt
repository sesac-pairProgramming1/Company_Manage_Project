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

    private var festivalList: MutableList<Festival> = deSerializationFestivalFile()
    override fun <T> update(t: T) {}

    override fun showList() {
        deSerializationFestivalFile()
        println(String.format("%39s","목록"))
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println("\t\t 주최 회사 \t\t\t│ \t\t\t 행사내용 \t\t\t │ \t\t 행사일 ")
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        festivalList.forEach {
            println("\t\t ${it.name} \t\t\t\t \t\t\t ${it.title} \t\t\t \t\t ${it.festivalDate}")
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

    override fun <T> delete(t: T) {
        val currentListCount = festivalList.size
        for (i in festivalList.indices) {
            if (t == festivalList[i].name) {
                festivalList.removeAt(i)
                break
            }
        }
        if (currentListCount - 1 == festivalList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
        }
    }



    fun choiceFestivalMenu(number: Int) {
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
                serializationFestivalFile()
            }
            2 -> {
                print("회사 이름 입력 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationFestivalFile()
            }
        }
    }
    fun deSerializationFestivalFile() = runBlocking {
        festivalList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/festival.ser")).use {
                it.readObject() as MutableList<Festival>
            }
        }
        festivalList
    }

    fun serializationFestivalFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/festival.ser")).use {
                with(it) {
                    writeObject(festivalList)
                    flush()
                }
            }
        }
    }
    companion object {
        private var INSTANCE: FestivalManager? = null
        fun initialize() {
            if(FestivalManager.INSTANCE ==null) {
                FestivalManager.INSTANCE = FestivalManager()
            } else {
                println("이미 초기화 되었습니다.")
            }
        }
    }
}

