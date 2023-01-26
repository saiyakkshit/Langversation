package com.cedzlabs.langversation.domain.repository

import com.cedzlabs.langversation.domain.models.FromToUser
import com.cedzlabs.langversation.domain.models.Message
import com.cedzlabs.langversation.domain.models.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
interface MessageRepository {
    /**
     * Save message in the database
     * @param message - [Message]
     * @return [Boolean] value result operation
     */
    suspend fun save(message: Message): Flow<Response<Boolean>>

    /**
     * Save latest message in the database
     * @param message - [Message]
     * @return [Boolean] value result operation
     */
    suspend fun saveLatestMessage(message: Message): Flow<Response<Boolean>>

    /**
     * Gets messages between users (if any)
     * @param - [FromToUser]
     * @return [Message]
     */
    suspend fun listenFromToUserMessages(fromToUser: FromToUser): Flow<Response<Message>>

    /**
     * Delete all messages between two users
     * @param fromToUser - [FromToUser]
     * @return [Boolean] value result operation
     */
    suspend fun deleteDialogBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>>

    /**
     * Removes data from recent messages
     * @param fromToUser - [FromToUser]
     * @return [Boolean] value result operation
     */
    suspend fun deleteLatestMessageBothUsers(fromToUser: FromToUser): Flow<Response<Boolean>>

    /**
     * Gets latest messages between users
     * @param fromUserUid - [String] user uid
     * @return [ArrayList]-[Message] all the latest messages of the user
     */
    suspend fun getLatestMessages(fromUserUid: String): Flow<Response<ArrayList<Message>>>
}