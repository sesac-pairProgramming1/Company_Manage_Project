package com.seasac.pair.UI.ArtistMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.play

fun showArtistMain() {
    val artistManager= ArtistManager.initArtistInstance()
    while (true) {
        showArtistTitleUI()


        artistManager.showList()
        showArtistMenuListUI()
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

fun showArtistMenuListUI() {
    print(String.format("%10s", "1. 가수 등록"))
    print(String.format("%11s", "2. 장르로 검색"))
    print(String.format("%10s", "3. 정보 수정"))
    print(String.format("%10s", "4. 계약 해지"))
    println(String.format("%10s","0. 이전 화면"))
}

fun showArtistTitleUI() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println(String.format("%40s", "가수 관리"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}

fun showArtistTableUI() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    println("\t\t 가수 이름 \t\t\t│ \t\t\t 장르 \t\t\t │ \t\t\t 데뷔일 ")
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
}
