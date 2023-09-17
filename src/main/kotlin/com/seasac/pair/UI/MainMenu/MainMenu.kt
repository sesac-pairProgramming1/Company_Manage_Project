package com.seasac.pair.UI.MainMenu

fun showMainMenu() {
    println("┌─────────────────────────────────────────────────────────────────────────────────┐")
    print("│")
    print(String.format("%50s", "HYBE Entertainment"))
    println(String.format("%32s", "│"))
    println("└─────────────────────────────────────────────────────────────────────────────────┘")
    print(String.format("%25s", "1. 계열사관리"))
    print(String.format("%12s", "2. 아티스트 관리"))
    print(String.format("%10s", "3. 행사 관리"))
    println(String.format("%10s", "4. 종료 하기"))


}
fun showEndLine() {
    println("===================================================================================")
}
