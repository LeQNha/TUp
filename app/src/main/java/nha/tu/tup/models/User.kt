package nha.tu.tup.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String = "",
    var avatar: String? = null,
    var username: String = "",
    var email: String? = null,
): Parcelable
