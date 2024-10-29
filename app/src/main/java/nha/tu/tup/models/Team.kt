package nha.tu.tup.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.time.LocalDate

@Parcelize
data class Team (
    var teamId : String = "",
    var teamName: String = "",
    val createdDate: Timestamp? = null,
    var leaderId: String? = null
) : Parcelable