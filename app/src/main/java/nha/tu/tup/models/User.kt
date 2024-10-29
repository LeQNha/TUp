package nha.tu.tup.models

data class User(
    val userId: String = "",
    var avatar: String? = null,
    var username: String = "",
    var email: String? = null,
)
