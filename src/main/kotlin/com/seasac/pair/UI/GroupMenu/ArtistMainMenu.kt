package com.seasac.pair.UI.GroupMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.play

fun showArtistMain() {
    val artistManager= ArtistManager()
    while (true) {
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println(String.format("%40s", "가수 관리"))
        println("└─────────────────────────────────────────────────────────────────────────────────┘")


        artistManager.showList()
        showArtistMenuList()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val artistMenuInput = ConsoleReader.consoleLineScanner()
        if (artistMenuInput == "0") {
            showEndLine()
            play()
            break
        }
        artistManager.choiceArtistMenu(artistMenuInput)
        showEndLine()
    }
}

fun showArtistMenuList() {
    print(String.format("%10s", "1. 가수 등록"))
    print(String.format("%10s", "2. 가수 검색"))
    print(String.format("%10s", "3. 정보 수정"))
    print(String.format("%10s", "4. 계약 해지"))
    println(String.format("%10s","0. 이전 화면"))

}