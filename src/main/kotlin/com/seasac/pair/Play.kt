package com.seasac.pair

import com.seasac.pair.UI.CompanyMenu.requestInput
import com.seasac.pair.UI.CompanyMenu.showMenuList
import com.seasac.pair.UI.MainMenu.selectMainMenu
import com.seasac.pair.UI.MainMenu.showMainMenu
import com.seasac.pair.manager.ArtistManager
import com.seasac.pair.manager.CompanyManager
import com.seasac.pair.manager.FestivalManager

fun play() {
   val companyManager=CompanyManager()
   val artistManager=ArtistManager.initialize()
   val festivalManager=FestivalManager.initialize()

   showMainMenu()

   val companylist=companyManager.deSerializationCompanyFile()
   showMenuList()
   val number = requestInput()
   companyManager.choiceCompanyMenu(number)


}