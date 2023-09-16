package com.seasac.pair.manager

import com.seasac.pair.Entity.Company
import com.seasac.pair.FeatureInterface
import common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class CompanyManager : FeatureInterface {

    // 반복되는 코드가 조금 많은 것 같다 (Manager 별로 코드 흐름이 같다)

    private var companyList: MutableList<Company> = mutableListOf()
    override fun <T> update(t: T) {
        var index=0
        for (i in companyList.indices) {
            if (t==companyList[i].name) {
                index=i
                break
            }
        }
        companyList[index]=companyInput()
    }

    override fun <T> showList() {
        companyList.forEach {
            println(
                "${it.name} \t\t ${it.field} \t\t ${it.representation} \t\t ${it.representation} \t\t" +
                        "${it.group}"
            )
        }
    }

    override fun <T> enroll(t: T) {
        companyList.add(t as Company)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        companyList.forEachIndexed { index, company ->
            if (company.field == t as String) {
                existFlag = true
                println(
                    "검색 결과 : ${companyList[index].name} \t\t" +
                            " ${companyList[index].field} \t\t ${companyList[index].representation} \t\t" +
                            "${companyList[index].address} \t\t ${companyList[index].group}"
                )
            }
        }
        if (existFlag) {
            println("일치하는 결과가 없습니다")
        }
    }

    override fun <T> delete(t: T) {
        val currentListCount = companyList.size
        for(i in companyList.indices) {
            if (companyList[i].name==t as String) {
                companyList.removeAt(i)
                break
            }
        }
        if (currentListCount-1 == companyList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }

    fun companyInput() : Company{

        print("회사이름 : ")
        val companyName = ConsoleReader.consoleLineScanner()
        print("분야 : ")
        val companyField = ConsoleReader.consoleLineScanner()
        print("대표 : ")
        val companyRepresentation = ConsoleReader.consoleLineScanner()
        print("장소 : ")
        val companyAddress = ConsoleReader.consoleLineScanner()
        print("아티스트 : ")
        val companyGroup = ConsoleReader.consoleLineScanner()
        return Company(companyName, companyField, companyRepresentation, companyAddress, companyGroup)
    }

    fun choiceFestivalMenu(number: Int) {
        when (number) {
            1 -> {
                //입력
                enroll(companyInput())
                serializationCompanyFile()
            }

            2 -> {
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationCompanyFile()
            }
        }
    }

    fun deSerializationCompanyFile() = runBlocking {
        companyList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/company.ser")).use {
                it.readObject() as MutableList<Company>
            }
        }
        companyList
    }

    fun serializationCompanyFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/festival.ser")).use {
                with(it) {
                    writeObject(companyList)
                    flush()
                }
            }
        }
        println(message)
    }

    companion object {
        private var INSTANCE: CompanyManager? = null

        fun initialize() {
            if (CompanyManager.INSTANCE == null) {
                CompanyManager.INSTANCE = CompanyManager()
            } else {
                println("이미 초기화 되었습니다.")
            }
        }
    }
}