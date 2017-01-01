package ru.annin.autorespect.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import ru.annin.autorespect.domain.value.UserRating
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Файл инкапсулирующий в себя модели данных.
 *
 * @author Pavel Annin.
 */

/**
 * Модель данных "Токен".
 *
 * @param token Пользовательский токен.
 */
data class TokenResponse(@JsonProperty("token", required = true) val token: String) : Serializable

/**
 * Модель данных "Информация о водителе".
 *
 * @param id Идентификатор ТС.
 * @param regionCode Код региона.
 * @param licencePlate Номер ТС.
 * @param commentCount Колличество комментариев.
 * @param like Колличество лайков.
 * @param dislike Количество дизлайков.
 * @param userRatingCode Флаг отражающий участие пользователя в оценки номера, возможные значения:
 * Like (1) / Dislike (-1) / Undefined(0).
 * @property userRating Пользовательский рейтинг.
 */
data class DriverInfoResponse(@JsonProperty("id", required = true) val id: Long,
                              @JsonProperty("regionCode", required = true) val regionCode: Int,
                              @JsonProperty("licencePlate", required = true) val licencePlate: String,
                              @JsonProperty("commentCount", required = true) val commentCount: Long,
                              @JsonProperty("like", required = true) val like: Long,
                              @JsonProperty("dislike", required = true) val dislike: Long,
                              @JsonProperty("userRating", required = true) private val userRatingCode: Int) : Serializable {

    val userRating: UserRating
        get() = UserRating.findByCode(userRatingCode)
}

/**
 * Модель данныех "Рейтинг водителя".
 *
 * @param userRatingCode Флаг отражающий участие пользователя в оценки номера, возможные значения:
 * Like (1) / Dislike (-1) / Undefined(0).
 * @property userRating Пользовательский рейтинг.
 */
data class DriverRatingResponse(@JsonProperty("userRating", required = true) val userRatingCode: Int) : Serializable {

    val userRating: UserRating
        get() = UserRating.findByCode(userRatingCode)
}

/**
 * Модель данных "Комментарий".
 *
 * @param id Идентификатор комментария.
 * @param userName Имя пользователя.
 * @param dateFormat Дата создания.
 * @param comment Текст комментария.
 * @param userComment Флаг указывающий, является ли этот комментирй этого пользователя или нет false.
 * @property date Дата создания.
 */
data class CommentResponse(@JsonProperty("id", required = true) val id: Long,
                           @JsonProperty("userName", required = true) val userName: String,
                           @JsonProperty("date", required = true) private val dateFormat: String,
                           @JsonProperty("comment", required = true) val comment: String,
                           @JsonProperty("userCommnet", required = true) val userComment: Boolean) : Serializable {
    val date: Date
        get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .apply { timeZone = TimeZone.getTimeZone("UTC") }
                .parse(dateFormat)
}

/**
 * Модель данных "Ошибка."
 *
 * @param message Сообщение об ошибке.
 */
data class ErrorResponse(@JsonProperty("error", required = true) val message: String) : Serializable