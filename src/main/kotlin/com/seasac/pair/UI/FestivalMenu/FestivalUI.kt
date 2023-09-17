package com.seasac.pair.UI.FestivalMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.FestivalManager
import com.seasac.pair.play


fun showFestivalMain() {
    val festivalManager = FestivalManager.initFestivalInstance()
    while (true) {
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println(String.format("%40s", "행사 관리"))
        println("└─────────────────────────────────────────────────────────────────────────────────┘")
        festivalManager.showList()
        showFestivalMenuListUI()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val festivalInput = ConsoleReader.consoleLineScanner()
        if (festivalInput == "0") {
            showEndLine()
            play()
            break
        }
        festivalManager.choiceFestivalMenu(festivalInput)
        showEndLine()
    }
}

fun showFestivalMenuListUI() {
    print(String.format("%10s", "1. 행사 등록"))
    print(String.format("%10s", "2. 행사 검색"))
    print(String.format("%10s", "3. 행사 수정"))
    print(String.format("%10s", "4. 행사 삭제"))
    println(String.format("%10s", "0. 이전 화면"))

}

fun showFestivalTableUI() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println("\t\t 주최 회사 \t\t\t│ \t\t\t 행사내용 \t\t\t │ \t\t 행사일 ")
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}

