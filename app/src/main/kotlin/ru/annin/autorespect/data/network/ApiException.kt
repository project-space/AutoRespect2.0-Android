package ru.annin.autorespect.data.network

import ru.annin.autorespect.domain.model.ErrorResponse

/**
 * Исключение в REST API.
 *
 * @author Pavel Annin.
 */
class ApiException(val isNetworkException: Boolean = false,
                   val code: Int = 0,
                   val errors: ErrorResponse? = null) : RuntimeException()