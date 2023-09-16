package com.seasac.pair.manager

import com.seasac.pair.Entity.Festival
import com.seasac.pair.FeatureInterface

class FestivalManager : FeatureInterface {

    private val festivalList: MutableList<Festival> = mutableListOf()
    override fun <T> update(t: T) {

    }

    override fun <T> showList() {
        festivalList.forEach {
            println("${it.name} \t\t ${it.title} \t\t ${it.enrolledDate}")
        }
    }

    override fun <T> enroll(t: T) {
        festivalList.add(t as Festival)
    }

    override fun <T> search(t: T) {
        var existFlag: Boolean = false
        var existIndex = 0
        festivalList.forEachIndexed { index, festival ->
            if (festival == t as Festival) {
                existFlag = true
                existIndex = index
            }
        }
        if (existFlag) {
            println(
                "검색 결과 : ${festivalList[existIndex].name} \t\t" +
                        " ${festivalList[existIndex].title} \t\t ${festivalList[existIndex].enrolledDate}"
            )
        } else {
            println("일치하는 결과가 없습니다.")
            // Label 이용?
        }
    }

    override fun <T> delete(t: T) {
        val currentListCount = festivalList.size
        festivalList.forEachIndexed { index, festival ->
            if (festival.name == (t as Festival).name) {
                festivalList.removeAt(index)
            }
        }
        if (currentListCount == festivalList.size - 1) {
            println("삭제 완료되었습니다")
        } else {
            println("다시 입력해주세요")
            //여기서 라벨을 이용?
        }
    }

    companion object {
        private var INSTANCE: FestivalManager? = null

        fun initialize() {
            if (INSTANCE == null) {
                INSTANCE = FestivalManager()
            }
        }

        fun get(): FestivalManager {
            return INSTANCE ?: throw IllegalStateException("Manager Must be Initialized")
        }
    }
}