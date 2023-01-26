package com.cedzlabs.langversation.domain.usecase

import com.cedzlabs.langversation.domain.models.Response
import com.cedzlabs.langversation.domain.repository.TranslateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class TranslateEnglishRussianTextUseCase(private val translateRepository: TranslateRepository) {
    suspend fun execute(text: String): Flow<Response<String>> {
        return translateRepository.translateEnglishRussianText(text = text)
    }
}