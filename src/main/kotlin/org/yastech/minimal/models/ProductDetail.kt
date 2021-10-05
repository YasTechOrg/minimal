package org.yastech.minimal.models

data class ProductDetail
(
    var id: String,
    var name: String?,
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