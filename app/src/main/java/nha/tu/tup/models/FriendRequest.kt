package nha.tu.tup.models

import com.google.firebase.Timestamp

data class FriendRequest(
    var friendRequestId: String? = null,
    val requestSenderId: String? = null,
    val requestReceiverId: String? = null,
    val requestDate: Timestamp? = null
)