package com.seasac.pair.common

import java.util.Scanner

class ConsoleReader {
    companion object {
        private lateinit var scanner : Scanner

        fun consoleNumberScanner() :Int {
            if (!this::scanner.isInitialized) {
                scanner= Scanner(System.`in`)
            }
            return scanner.nextInt()
        }

        fun consoleLineScanner() : String {
            if (!this::scanner.isInitialized) {
                scanner= Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }
}