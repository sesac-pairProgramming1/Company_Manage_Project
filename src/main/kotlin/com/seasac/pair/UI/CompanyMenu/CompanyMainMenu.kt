package com.seasac.pair.UI.CompanyMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.requestInput
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.play

fun showCompanyMain() {
    val companyManager=CompanyManager()
    while (true) {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println(String.format("%40s", "회사 관리"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")


    companyManager.showList()
        showCompanyMenuList()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val companyMenuInput = requestInput()
        if (companyMenuInput==0) {
            showEndLine()
            play()
            break
        }
        companyManager.choiceCompanyMenu(companyMenuInput)
        showEndLine()
    }
}

fun showCompanyMenuList() {
    print(String.format("%10s", "1. 계열사 등록"))
    print(String.format("%10s", "2. 계열사 검색"))
    print(String.format("%10s", "3. 계열사 수정"))
    print(String.format("%10s", "4. 계열사 삭제"))
    println(String.format("%10s","0. 이전 화면"))
}





