package org.yastech.minimal.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.yastech.minimal.tables.User

data class ProductDetail
(
    var id: String,
    var image: String?,
    var code: String?,
    var off: Boolean?,
    var offValue: Long?,
    var price: Long?,
    var publisher: String?,
    var colors: MutableList<String>,
    var like: MutableList<String>?,
    var categories: MutableList<String>?,
)