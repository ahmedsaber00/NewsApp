package com.saber.sabernews.data.remote.base.mapper

interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

}