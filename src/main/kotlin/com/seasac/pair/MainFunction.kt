package com.seasac.pair

import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.manager.FestivalManager


fun main() {
//    var line : String? = ConsoleReader.consoleLineScanner()
    val companyManager = CompanyManager()
    val number : String= ConsoleReader.consoleLineScanner()

    companyManager.choiceCompanyMenu(number.toInt())
    val list = companyManager.deSerializationCompanyFile()
    println(list)





}
