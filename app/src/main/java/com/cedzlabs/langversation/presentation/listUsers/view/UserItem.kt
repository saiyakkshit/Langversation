package com.cedzlabs.langversation.presentation.listUsers.view

import android.view.View
import com.cedzlabs.langversation.R
import com.cedzlabs.langversation.databinding.ItemContainerUserBinding
import com.cedzlabs.langversation.domain.models.UserDetailsPublic
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class UserItem(val user: UserDetailsPublic) : BindableItem<ItemContainerUserBinding>() {
    override fun initializeViewBinding(view: View): ItemContainerUserBinding {
        return ItemContainerUserBinding.bind(view)
    }

    override fun bind(viewBinding: ItemContainerUserBinding, position: Int) {
        viewBinding.textViewFullname.text = user.fullname
        viewBinding.textViewEmail.text = user.email

        if (user.photoProfileUrl.isNotEmpty()) {
            Picasso.get().load(user.photoProfileUrl).transform(CropCircleTransformation())
                .into(viewBinding.shapeableImageViewProfileImage)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_container_user
    }
}