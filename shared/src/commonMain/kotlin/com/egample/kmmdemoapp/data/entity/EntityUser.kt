import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntityUser(
    @SerialName("name")
    val name: String,
)