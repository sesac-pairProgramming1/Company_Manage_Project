package com.seasac.pair

interface FeatureInterface {

    fun<T> update(t: T)

    fun<T> showList()

    fun<T> enroll(t: T)

    fun<T> search(t:T)

    fun<T> delete(t : T)
}