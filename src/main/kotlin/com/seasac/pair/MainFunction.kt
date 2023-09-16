package com.seasac.pair

import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.manager.FestivalManager


fun main() {
//    var line : String? = ConsoleReader.consoleLineScanner()
    val artistManager = ArtistManager()
    val number : String= ConsoleReader.consoleLineScanner()

    artistManager.choiceArtistMenu(number.toInt())
    val list = artistManager.deSerializationArtistFile()
    println(list)


}
