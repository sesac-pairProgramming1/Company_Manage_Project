package com.seasac.pair.UI.CompanyMenu

import com.seasac.pair.UI.FestivalMenu.showFestivalMainMenu
import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader

fun showCompanyMainMenu() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    print("│")
    print(String.format("%40s", "회사 관리"))
    println(String.format("%39s", "│"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}

fun showMenuList() {
    print(String.format("%10s", "1. 계열사 등록"))
    print(String.format("%10s", "2. 계열사 검색"))
    print(String.format("%10s", "3. 계열사 수정"))
    println(String.format("%10s", "4. 계열사 삭제"))
}

fun requestInput():Int {
    val number=ConsoleReader.consoleLineScanner()
    return number.toInt()
}




