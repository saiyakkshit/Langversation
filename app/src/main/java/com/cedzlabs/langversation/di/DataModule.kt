package com.cedzlabs.langversation.di

import com.cedzlabs.langversation.data.repository.*
import com.cedzlabs.langversation.data.storage.*
import com.cedzlabs.langversation.data.storage.firebase.FirebaseMessageStorage
import com.cedzlabs.langversation.data.storage.firebase.FirebaseUserAuthStorage
import com.cedzlabs.langversation.data.storage.firebase.FirebaseUserDetailsStorage
import com.cedzlabs.langversation.data.storage.firebase.FirebaseUserImagesStorage
import com.cedzlabs.langversation.data.storage.mlkit.MlKitTranslateStorage
import com.cedzlabs.langversation.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesUserAuthStorage(): UserAuthStorage {
        return FirebaseUserAuthStorage()
    }

    @Provides
    @Singleton
    fun providesUserAuthRepository(userAuthStorage: UserAuthStorage): UserAuthRepository {
        return UserAuthRepositoryImpl(userAuthStorage = userAuthStorage)
    }

    @Provides
    @Singleton
    fun providesUserDetailsStorage(): UserDetailsStorage {
        return FirebaseUserDetailsStorage()
    }

    @Provides
    @Singleton
    fun providesUserDetailsRepository(userDetailsStorage: UserDetailsStorage): UserDetailsRepository {
        return UserDetailsRepositoryImpl(userDetailsStorage = userDetailsStorage)
    }

    @Provides
    @Singleton
    fun providesUserImagesStorage(): UserImagesStorage {
        return FirebaseUserImagesStorage()
    }

    @Provides
    @Singleton
    fun providesUserImagesRepository(userImagesStorage: UserImagesStorage): UserImagesRepository {
        return UserImagesRepositoryImpl(userImagesStorage = userImagesStorage)
    }

    @Provides
    @Singleton
    fun providesTranslateStorage(): TranslateStorage {
        return MlKitTranslateStorage()
    }

    @Provides
    @Singleton
    fun providesTranslateRepository(translateStorage: TranslateStorage): TranslateRepository {
        return TranslateRepositoryImpl(translateStorage = translateStorage)
    }

    @Provides
    @Singleton
    fun providesMessageStorage(): MessageStorage {
        return FirebaseMessageStorage()
    }

    @Provides
    @Singleton
    fun providesMessageRepository(messageStorage: MessageStorage): MessageRepository {
        return MessageRepositoryImpl(messageStorage = messageStorage)
    }
}