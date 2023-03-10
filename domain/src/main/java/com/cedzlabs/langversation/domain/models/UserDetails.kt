package com.cedzlabs.langversation.domain.models

data class UserDetails(
    var uid: String = "",
    var fullname: String = "",
    var email: String = "",
    var password: String = "",
    var photoProfileUrl: String = ""
)