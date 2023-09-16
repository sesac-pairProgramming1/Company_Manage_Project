package com.seasac.pair

import com.seasac.pair.Entity.Artist
import com.seasac.pair.Entity.Company
import com.seasac.pair.UI.CompanyMenu.requestInput
import com.seasac.pair.UI.CompanyMenu.showCompanyMainMenu
import com.seasac.pair.UI.CompanyMenu.showMenuList
import com.seasac.pair.UI.GroupMenu.showArtistMainMenu
import com.seasac.pair.UI.MainMenu.showMainMenu
import com.seasac.pair.common.ConsoleReader
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.manager.FestivalManager

fun play() {

   println("┌─────────────────────────────────────────────────────────────────────────────────┐")
   print("│")
   print(String.format("%50s", "HYBE Entertainment"))
   println(String.format("%32s", "│"))
   println("└─────────────────────────────────────────────────────────────────────────────────┘")
   print(String.format("%25s", "1. 계열사관리"))
   print(String.format("%10s", "2. 그룹 관리"))
   print(String.format("%10s", "3. 행사 관리"))
   println(String.format("%10s", "4. 종료 하기"))
   while(true) {
      val number : Int = ConsoleReader.consoleLineScanner().toInt()
      when(number) {
         1-> {
            val companyManager=CompanyManager()
            showCompanyMainMenu()
            companyManager.showList<Company>()



         }
         2-> {
            val artistManager=ArtistManager()
            showArtistMainMenu()
            artistManager.showList<Artist>()
         }

         4-> {
            return
         }
      }

   }






}