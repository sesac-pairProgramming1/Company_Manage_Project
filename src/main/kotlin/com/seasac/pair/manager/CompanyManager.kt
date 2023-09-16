package com.seasac.pair.manager

import com.seasac.pair.Entity.Company
import com.seasac.pair.FeatureInterface

class CompanyManager : FeatureInterface {

    // 반복되는 코드가 조금 많은 것 같다 (Manager 별로 코드 흐름이 같다)

    private val companyList: MutableList<Company> = mutableListOf()
    override fun <T> update(t: T) {

    }

    override fun <T> showList() {
        companyList.forEach {
            println(
                "${it.name} \t\t ${it.field} \t\t ${it.representation} \t\t ${it.representation} \t\t" +
                        "${it.groups}"
            )
        }
    }

    override fun <T> enroll(t: T) {
        companyList.add(t as Company)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        var existIndex = 0
        companyList.forEachIndexed { index, company ->
            if (company == t as Company) {
                existFlag = true
                existIndex = index
            }
        }
        if (existFlag) {
            println(
                "검색 결과 : ${companyList[existIndex].name} \t\t" +
                        " ${companyList[existIndex].field} \t\t ${companyList[existIndex].representation} \t\t" +
                        "${companyList[existIndex].address} \t\t ${companyList[existIndex].groups}"
            )
        } else {
            println("일치하는 결과가 없습니다.")
            // Label 이용?
        }
    }

    override fun <T> delete(t: T) {
        val currentListCount = companyList.size
        companyList.forEachIndexed { index, company ->
            if (company.name == (t as Company).name) {
                companyList.removeAt(index)
            }
        }
        if (currentListCount == companyList.size - 1) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }

}