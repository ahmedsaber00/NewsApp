package com.saber.sabernews.domain.base.interactor

abstract class UseCase<R, in P> {

    operator fun invoke(params: P): R = execute(params)

    @Throws(RuntimeException::class)
    protected abstract fun execute(params: P): R
}