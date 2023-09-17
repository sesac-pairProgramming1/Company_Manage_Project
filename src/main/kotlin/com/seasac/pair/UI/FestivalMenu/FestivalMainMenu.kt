package com.seasac.pair.UI.FestivalMenu

import com.seasac.pair.UI.MainMenu.showEndLine
import com.seasac.pair.common.requestInput
import com.seasac.pair.manager.FestivalManager
import com.seasac.pair.play


fun showFestivalMain() {
    val festivalManager= FestivalManager()
    while (true) {
        println("┌─────────────────────────────────────────────────────────────────────────────────┐")
        println(String.format("%40s", "행사 관리"))
        println("└─────────────────────────────────────────────────────────────────────────────────┘")


        festivalManager.showList()
        showArtistMenuList()
        print("원하시는 메뉴를 선택해 주세요 : ")
        val festivalInput = requestInput()
        if (festivalInput==0) {
            showEndLine()
            play()
            break
        }
        festivalManager.choiceFestivalMenu(festivalInput)
        showEndLine()
    }
}

fun showArtistMenuList() {
    print(String.format("%10s", "1. 행사 등록"))
    print(String.format("%10s", "2. 행사 검색"))
    print(String.format("%10s", "3. 행사 삭제"))
    println(String.format("%10s","0. 이전 화면"))

}

