package ru.annin.autorespect.domain.value

/**
 * Пользовательский рейтинг:
 *  - LIKE
 *  - DISLIKE
 *  - UNDEFINED
 *
 * @author Pavel Annin.
 */
enum class UserRating(val code: Int) {
    LIKE(1),
    DISLIKE(-1),
    UNDEFINED(0);

    companion object {
        fun findByCode(code: Int): UserRating {
            return values().first { it.code == code }
        }
    }
}