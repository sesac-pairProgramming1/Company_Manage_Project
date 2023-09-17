package com.seasac.pair.manager

import com.seasac.pair.Entity.Company
import com.seasac.pair.FeatureInterface
import com.seasac.pair.UI.CompanyMenu.showCompanyMain
import com.seasac.pair.UI.CompanyMenu.showCompanyMenuList
import com.seasac.pair.common.ConsoleReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class CompanyManager : FeatureInterface {

    // 반복되는 코드가 조금 많은 것 같다 (Manager 별로 코드 흐름이 같다)

    private var companyList: MutableList<Company> = deSerializationCompanyFile()

    override fun <T> update(t: T) {
        var index = 0
        for (i in companyList.indices) {
            if (t == companyList[i].name) {
                index = i
                break
            }
        }
        companyList[index] = companyInput()
    }

    override fun showList() {
        deSerializationCompanyFile()
        println(String.format("%39s","목록"))
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println("\t  회사 이름  \t │ \t  사업명  \t │ \t  대표  \t │ \t    주소    \t │ \t  대표가수 ")
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        companyList.forEach {
            println(
                "\t  ${it.name}  \t \t\t  ${it.field}  \t \t  ${it.representation}  \t\t ${it.address}\t" +
                        "\t\t${it.group}"
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
        if (!existFlag) {
            println("일치하는 결과가 없습니다.")
        }
    }

    override fun <T> delete(t: T) {
        val currentListCount = companyList.size
        for (i in companyList.indices) {
            if (t == companyList[i].name) {
                companyList.removeAt(i)
                break
            }
        }
        if (currentListCount - 1 == companyList.size) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
        }
    }

    fun companyInput(): Company {
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

    fun choiceCompanyMenu(number: Int) {
        when (number) {
            1 -> {
                //입력
                enroll(companyInput())
                SerializationCompanyFile()
            }

            2 -> {
                print("분야를 입력하세요 : ")
                val companyField = ConsoleReader.consoleLineScanner()
                search(companyField)

            }

            3 -> {
                print("수정할 회사명 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                update(companyName)
                SerializationCompanyFile()
            }

            4 -> {
                print("삭제할 회사명 : ")
                val companyName = ConsoleReader.consoleLineScanner()
                delete(companyName)
                SerializationCompanyFile()
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

    fun SerializationCompanyFile() = runBlocking {
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

        fun initialize() {
            if (CompanyManager.INSTANCE == null) {
                CompanyManager.INSTANCE = CompanyManager()
            } else {
                println("이미 초기화 되었습니다.")
            }
        }
    }



}