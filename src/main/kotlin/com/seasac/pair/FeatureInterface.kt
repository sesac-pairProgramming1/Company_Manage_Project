package com.seasac.pair

/**
 * Feature interface
 * CRUD 기능을 위한 인터페이스
 * @constructor Create empty Feature interface
 */
interface FeatureInterface {
    fun<T> enroll(t: T)

    fun<T> search(t:T)

    fun<T> update(t: T)

    fun<T> delete(t : T)

    fun showList()



}