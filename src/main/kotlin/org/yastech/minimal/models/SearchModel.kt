package org.yastech.minimal.models

data class SearchModel
(
    var text: String?,
    var categories: MutableList<String>?,
    var brands: MutableList<String>?,
    var minPrice: Long,
    var maxPrice: Long
)