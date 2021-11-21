package com.pklein.prototype.utils

import androidx.lifecycle.*


fun <T> LiveData<T>.bind(owner: LifecycleOwner, onObserved: (T) -> Unit) {
    this.observe(owner, Observer {
        onObserved.invoke(it)
    })
}

fun <S, T> LiveData<S>.map(mapFunction: (S) -> T): LiveData<T> =
    Transformations.map(this, mapFunction)

inline fun <T> LiveData<T>.filter(crossinline predicate: (T?) -> Boolean): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    mutableLiveData.addSource(this) {
        if (predicate(it))
            mutableLiveData.value = it
    }
    return mutableLiveData
}

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    var latestValue: T? = null
    mutableLiveData.addSource(this) {
        if (latestValue != it) {
            mutableLiveData.value = it
            latestValue = it
        }
    }
    return mutableLiveData
}