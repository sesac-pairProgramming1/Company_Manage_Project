package com.seasac.pair

interface FeatureInterface {
    fun<T> update(t: T)

    fun<T> showList() : MutableList<T>

    fun<T> enroll(t: T)

    fun<T> search(t:T) : T

    fun delete()
}