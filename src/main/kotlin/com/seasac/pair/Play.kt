package com.seasac.pair

import com.seasac.pair.UI.CompanyMenu.showCompanyMain
import com.seasac.pair.UI.FestivalMenu.showFestivalMain
import com.seasac.pair.UI.GroupMenu.showArtistMain
import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.UI.MainMenu.showMainMenu
import com.seasac.pair.common.ConsoleReader
import java.lang.Exception

fun play() {
    while (true) {
        showMainMenu()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val input = ConsoleReader.consoleLineScanner()
        showEndLine()
        when (input) {
            "1" -> {
                showCompanyMain()
                break
            }

            "2" -> {
                showArtistMain()
                break
            }

            "3" -> {
                showFestivalMain()
                break
            }

            "4" -> {
                println("프로그램을 종료합니다")
                showEndLine()
                return
            }
            else -> { println("다시 입력해주세요")}
        }
    }
}