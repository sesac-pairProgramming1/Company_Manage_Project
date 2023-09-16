package com.seasac.pair

import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.manager.FestivalManager


fun main() {
//    var line : String? = ConsoleReader.consoleLineScanner()
    val festivalManager = FestivalManager()
    val number : String= ConsoleReader.consoleLineScanner()

    festivalManager.abcde(number.toInt())

    festivalManager.deSerializationArtistFile()




}
