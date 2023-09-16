package com.seasac.pair.UI.MainMenu

import com.seasac.pair.UI.CompanyMenu.showCompanyMainMenu
import com.seasac.pair.UI.FestivalMenu.showFestivalMainMenu
import com.seasac.pair.UI.GroupMenu.showGroupMainMenu
import com.seasac.pair.common.ConsoleReader

fun showMainMenu() {
    println("┌────────────────────────────────────────────────────────────┐")
    print("│")
    print(String.format("%40s","HYBE Entertainment"))
    println(String.format("%21s","│"))
    println("├────────────────────────────────────────────────────────────┤")
    print("│")
    print(String.format("%33s","1. 회사 관리"))
    println(String.format("%25s","│"))
    print("│")
    print(String.format("%33s","2. 그룹 관리"))
    println(String.format("%25s","│"))
    print("│")
    print(String.format("%33s","3. 행사 관리"))
    println(String.format("%25s","│"))
    print("│")
    print(String.format("%33s","4. 종료 하기"))
    println(String.format("%25s","│"))
    println("└────────────────────────────────────────────────────────────┘")
    selectMainMenu()
}

fun selectMainMenu() {
    var number : Int =0
    print("원하는 번호를 입력해주세요 : ")
    number=ConsoleReader.consoleNumberScanner()
    when(number) {
        1-> showCompanyMainMenu()
        2-> showGroupMainMenu()
        3-> showFestivalMainMenu()
        else -> return
    }
}