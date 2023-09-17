package com.seasac.pair.UI.CompanyMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.play

fun showCompanyMain() {
    val companyManager=CompanyManager.initCompanyInstance()
    while (true) {
        showCompanyTitleUI()


    companyManager.showList()
        showCompanyMenuListUI()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val companyMenuInput = ConsoleReader.consoleLineScanner()
        if (companyMenuInput == "0") {
            showEndLine()
            play()
            break
        }
        companyManager.choiceCompanyMenu(companyMenuInput)
        showEndLine()
    }
}

fun showCompanyMenuListUI() {
    print(String.format("%10s", "1. 계열사 등록"))
    print(String.format("%10s", "2. 계열사 검색"))
    print(String.format("%10s", "3. 계열사 수정"))
    print(String.format("%10s", "4. 계열사 삭제"))
    println(String.format("%10s","0. 이전 화면"))
}

fun showCompanyTitleUI() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println(String.format("%40s", "회사 관리"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}

fun showCompanyTableUI() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println("\t  회사 이름  \t │ \t  사업명  \t │ \t  대표  \t │ \t    주소    \t │ \t  대표가수 ")
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}




