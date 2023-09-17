package com.seasac.pair.manager

import com.seasac.pair.Entity.Festival
import com.seasac.pair.FeatureInterface
import com.seasac.pair.UI.FestivalMenu.showFestivalTableUI
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
    override fun <T> update(t: T) {
        var index = -1
        for (i in festivalList.indices) {
            if (t == festivalList[i].title) {
                index = i
                break
            }
        }
        if (index != -1) festivalList[index] = festivalInput() else println("해당하는 행사가 없습니다.")
    }

    override fun showList() {
        deSerializationFestivalFile()
        println(String.format("%39s", "목록"))
        showFestivalTableUI()
        festivalList.forEach {
            println("\t\t ${it.name} \t\t\t\t ${it.title} \t\t\t \t\t ${it.festivalDate}")
        }
    }

    override fun <T> enroll(t: T) {
        festivalList.add(t as Festival)
    }

    override fun <T> search(t: T) {
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println(String.format("%40s", "검색결과"))
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        showFestivalTableUI()
        val newList = festivalList.asSequence().filter {  festival ->
            festival.name.startsWith(t as String)
        }.map {
            println("\t\t ${it.name} \t\t\t\t ${it.title} \t\t\t \t\t ${it.festivalDate}")
        }.toList()
        if (newList.isEmpty()) {
            println("검색결과가 없습니다")
        }
        Thread.sleep(3000)
    }

    override fun <T> delete(t: T) {
        val currentListCount = festivalList.size
        for (i in festivalList.indices) {
            if (t == festivalList[i].title) {
                festivalList.removeAt(i)
                break
            }
        }
        if (currentListCount - 1 == festivalList.size) {
            println("삭제 완료되었습니다")
            Thread.sleep(1000)
        } else {
            println("다시 입력해주세요")
            Thread.sleep(1000)
        }
    }

    private fun festivalInput(): Festival {
        print("회사입력 : ")
        val companyName = ConsoleReader.consoleLineScanner()
        print("행사 제목 : ")
        val festivalTitle = ConsoleReader.consoleLineScanner()
        print("개최일 ex)2000.01.01: ")
        val festivalDate = ConsoleReader.consoleLineScanner()
        return Festival(companyName, festivalTitle , festivalDate)
    }

    fun choiceFestivalMenu(number: String) {
        when (number) {
            "1" -> {
                enroll(festivalInput())
                serializationFestivalFile()
            }

            "2" -> {
                print("회사 입력 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                search(companyName)
            }
            "3" -> {
                print("수정할 행사명 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                update(companyName)
                serializationFestivalFile()
            }

            "4" -> {
                print("행사 제목 입력 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationFestivalFile()
            }

            else -> {
                println("다시 입력해주세요")
                Thread.sleep(1000)

            }
        }
    }

    private fun deSerializationFestivalFile() = runBlocking {
        festivalList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/festival.ser")).use {
                it.readObject() as MutableList<Festival>
            }
        }
        festivalList
    }

    private fun serializationFestivalFile() = runBlocking {
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

        fun initFestivalInstance(): FestivalManager =
            INSTANCE ?: synchronized(this) {
                return@synchronized INSTANCE ?: FestivalManager().also {
                    INSTANCE = it
                }
            }

    }
}

