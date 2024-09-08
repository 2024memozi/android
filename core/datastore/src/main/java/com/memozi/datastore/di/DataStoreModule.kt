package com.memozi.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.memozi.datastore.MemoziLocalData
import com.memozi.datastore.MemoziLocalDataSerializer
import com.memozi.datastore.token.AuthToken
import com.memozi.datastore.token.TokenDataSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesUserDataStore(
        @ApplicationContext context: Context,
        userDataSerializer: MemoziLocalDataSerializer
    ): DataStore<MemoziLocalData> =
        DataStoreFactory.create(
            serializer = userDataSerializer
        ) {
            context.dataStoreFile("userdata.json")
        }

    @Provides
    @Singleton
    fun providesTokenDataStore(
        @ApplicationContext context: Context,
        tokenDataSerializer: TokenDataSerializer
    ): DataStore<AuthToken> =
        DataStoreFactory.create(
            serializer = tokenDataSerializer
        ) {
            context.dataStoreFile("token.json")
        }
}
