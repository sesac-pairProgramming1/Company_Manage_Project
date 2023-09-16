package com.seasac.pair.UI.MainMenu

import com.seasac.pair.UI.CompanyMenu.showCompanyMainMenu
import com.seasac.pair.UI.FestivalMenu.showFestivalMainMenu
import com.seasac.pair.UI.GroupMenu.showGroupMainMenu
import com.seasac.pair.common.ConsoleReader

fun showMainMenu() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    print("│")
    print(String.format("%50s", "HYBE Entertainment"))
    println(String.format("%32s", "│"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
    print(String.format("%25s", "1. 계열사관리"))
    print(String.format("%10s", "2. 그룹 관리"))
    print(String.format("%10s", "3. 행사 관리"))
    println(String.format("%10s", "4. 종료 하기"))
    selectMainMenu()

}
fun showEndLine() {
    println("===================================================================================")
}
fun selectMainMenu() {
    var number: Int = 0
    print("메뉴 선택 : ")
    number = ConsoleReader.consoleNumberScanner()
    when (number) {
        1 ->  {
            showEndLine()
            showCompanyMainMenu()
        }
        2 -> {
            showEndLine()
            showCompanyMainMenu()
        }
        3 -> {
            showEndLine()
            showFestivalMainMenu()
        }
        else -> return
    }
}