package com.memozi.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoneAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth
