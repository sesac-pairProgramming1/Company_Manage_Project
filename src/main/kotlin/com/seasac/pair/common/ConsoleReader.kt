package com.seasac.pair.common

import java.util.Scanner

/**
 * Console reader
 * Scanner 는 객체 하나만 생성되는 것이 좋다
 * @constructor Create empty Console reader
 */
class ConsoleReader {
    companion object {
        private lateinit var scanner : Scanner
        fun consoleLineScanner() : String {
            if (!this::scanner.isInitialized) {
                scanner= Scanner(System.`in`)
            }
            return scanner.nextLine().replace(" ","")
        }
    }
}