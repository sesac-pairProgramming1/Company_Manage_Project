package com.seasac.pair.manager

import com.seasac.pair.Entity.Company
import com.seasac.pair.FeatureInterface
import com.seasac.pair.UI.CompanyMenu.showCompanyTableUI
import com.seasac.pair.common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

class CompanyManager : FeatureInterface {

    private var companyList: MutableList<Company> = deSerializationCompanyFile()

    override fun <T> enroll(t: T) {
        companyList.add(t as Company)
    }

    override fun showList() {
        deSerializationCompanyFile()
        println(String.format("%39s", "목록"))
        showCompanyTableUI()
        companyList.forEach {
            println(
                "\t  ${it.name}  \t\t  ${it.field}  \t \t  ${it.representation}  \t\t ${it.address}\t" +
                        "\t\t${it.group}"
            )
        }
    }

    override fun <T> search(t: T) {
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println(String.format("%40s", "검색결과"))
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        showCompanyTableUI()
        val newList = companyList.asSequence().filter { company ->
            company.field!!.startsWith(t as String, true)
        }.map {
            println(
                "\t  ${it.name}  \t\t  ${it.field}  \t \t  ${it.representation}  \t\t ${it.address}\t" +
                        "\t\t${it.group}"
            )
        }.toList()
        if (newList.isEmpty()) {
            println("검색결과가 없습니다")
        }
        Thread.sleep(3000)
    }

    override fun <T> update(t: T) {
        var index = -1
        for (i in companyList.indices) {
            if (t == companyList[i].name) {
                index = i
                break
            }
        }
        if (index != -1) companyList[index] = companyInput() else println("해당하는 회사가 없습니다.")
    }

    override fun <T> delete(t: T) {
        val currentListCount = companyList.size
        for (i in companyList.indices) {
            if (companyList[i].name.equals(t as String, true)) {
                companyList.removeAt(i)
                break
            }
        }
        if (currentListCount - 1 == companyList.size) {
            println("삭제 완료되었습니다")
            Thread.sleep(1000)
        } else {
            println("다시 입력해주세요")
            Thread.sleep(1000)
        }
    }

    private fun companyInput(): Company {
        print("회사이름 : ")
        val companyName = ConsoleReader.consoleLineScanner()
        print("사업명 : ")
        val companyField = ConsoleReader.consoleLineScanner()
        print("대표 : ")
        val companyRepresentation = ConsoleReader.consoleLineScanner()
        print("장소 : ")
        val companyAddress = ConsoleReader.consoleLineScanner()
        print("아티스트 : ")
        var companyMainArist = ConsoleReader.consoleLineScanner()
        if (companyMainArist == "") companyMainArist = "없음"
        return Company(companyName, companyField, companyRepresentation, companyAddress, companyMainArist)
    }

    fun choiceCompanyMenu(number: String) {
        when (number) {
            "1" -> {
                //입력
                enroll(companyInput())
                serializationCompanyFile()
            }

            "2" -> {
                print("사업명를 입력하세요 : ")
                val companyField = ConsoleReader.consoleLineScanner()
                search(companyField)

            }

            "3" -> {
                print("수정할 회사명 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                update(companyName)
                serializationCompanyFile()
            }

            "4" -> {
                print("삭제할 회사명 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                serializationCompanyFile()
            }

            else -> {
                println("다시 입력해주세요")
                Thread.sleep(1000)
            }
        }
    }

    private fun deSerializationCompanyFile() = runBlocking {
        companyList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./serialization/company.ser")).use {
                it.readObject() as MutableList<Company>
            }
        }
        companyList
    }

    private fun serializationCompanyFile() = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./serialization/company.ser")).use {
                with(it) {
                    writeObject(companyList)
                    flush()
                }
            }
        }
    }

    companion object {
        private var INSTANCE: CompanyManager? = null
        fun initCompanyInstance(): CompanyManager =
            INSTANCE ?: synchronized(this) {
                return@synchronized INSTANCE ?: CompanyManager().also {
                    INSTANCE = it
                }
            }
    }
}