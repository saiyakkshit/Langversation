package com.cedzlabs.langversation.presentation.detailsMessages.view

import android.view.View
import com.cedzlabs.langversation.R
import com.cedzlabs.langversation.databinding.ItemContainerRecipientMessageBinding
import com.cedzlabs.langversation.domain.models.Message
import com.xwray.groupie.viewbinding.BindableItem
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class MessageToItem(private val message: Message) :
    BindableItem<ItemContainerRecipientMessageBinding>() {
    override fun bind(viewBinding: ItemContainerRecipientMessageBinding, position: Int) {
        viewBinding.textViewMessage.text = message.englishMessage

        val netDate = Date(Timestamp(message.timestamp).time)
        val date = SimpleDateFormat("dd/MM/yy hh:mm a").format(netDate)
        viewBinding.textViewDateTime.text = date

        var textEnglish: Boolean = true
        viewBinding.imageviewTranslated.setOnClickListener {
            if (textEnglish) {
                viewBinding.textViewMessage.text = message.russianMessage
                textEnglish = false
            } else {
                viewBinding.textViewMessage.text = message.englishMessage
                textEnglish = true
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_container_recipient_message
    }

    override fun initializeViewBinding(view: View): ItemContainerRecipientMessageBinding {
        return ItemContainerRecipientMessageBinding.bind(view)
    }
}