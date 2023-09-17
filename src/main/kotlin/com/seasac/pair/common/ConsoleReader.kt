package com.seasac.pair.common

import java.util.Scanner

class ConsoleReader {
    companion object {
        private lateinit var scanner : Scanner
        fun consoleLineScanner() : String {
            if (!this::scanner.isInitialized) {
                scanner= Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }
}