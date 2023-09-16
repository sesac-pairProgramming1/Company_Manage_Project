package com.seasac.pair.UI.FestivalMenu

import com.seasac.pair.UI.CompanyMenu.showCompanyMainMenu
import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader

fun showFestivalMainMenu() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    print("│")
    print(String.format("%50s", "행사 관리"))
    println(String.format("%32s", "│"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
    print(String.format("%31s", "1 행사목록"))
    print(String.format("%31s", "2 행사등록"))
    print(String.format("%31s", "3 행사검색"))

}

    fun selectFestivalMenu() {
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

