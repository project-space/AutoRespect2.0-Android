package ru.annin.autorespect.data.repository

import ru.annin.autorespect.data.network.AutoRespectApi
import ru.annin.autorespect.data.network.AutoRespectApiService
import ru.annin.autorespect.data.repository.RestApiRepository.token
import ru.annin.autorespect.domain.model.CommentResponse
import ru.annin.autorespect.domain.model.DriverInfoResponse
import ru.annin.autorespect.domain.model.DriverRatingResponse
import ru.annin.autorespect.domain.model.TokenResponse
import ru.annin.autorespect.domain.value.UserRating
import rx.Observable

/**
 * Репозиторий инкапсулирующий в себя работу с сервером.
 *
 * @property token Пользовательский токен.
 *
 * @author Pavel Annin.
 */
object RestApiRepository {

    // Component's
    private val service: AutoRespectApi = AutoRespectApiService.service

    // Data
    var token: String = ""

    /**
     * Регистрация нового пользователя.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     * @param userName Имя пользователя.
     */
    fun registration(login: String,
                     password: String,
                     userName: String): Observable<TokenResponse> {
        return service.registration(login, password, userName)
    }

    /**
     * Авторизация.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     */
    fun authorization(login: String,
                      password: String): Observable<TokenResponse> {
        return service.authorization(login, password)
    }

    /**
     * Информация о водителе.
     *
     * @param regionCode Код региона.
     * @param numberCode Номер ТС.
     */
    fun getDriverInfo(regionCode: Int,
                      numberCode: String): Observable<DriverInfoResponse> {
        return service.getDriverInfo(token, regionCode, numberCode)
    }

    /**
     * Оценить водителя.
     *
     * @param id Идентификатор ТС.
     * @param rating Пользовательская оценка.
     */
    fun sendDriverRating(id: Long,
                         rating: UserRating): Observable<DriverRatingResponse> {
        return service.sendDriverRating(token, id, rating.code)
    }

    /**
     * Комментарии к ТС.
     *
     * @param id Идентификатор ТС.
     * @param limit Количество комментариев в ответе.
     * @param offset Смещение комментариев.
     */
    fun getDriveComments(id: Long,
                         limit: Int,
                         offset: Int): Observable<List<CommentResponse>> {
        return service.getDriveComments(token, id, limit, offset)
    }

    /**
     * Создание комментария к ТС.
     *
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    fun createDriveComment(id: Long,
                           comment: String): Observable<Unit> {
        return service.createDriveComment(token, id, comment)
    }

    /**
     * Изменение комментария к ТС.
     *
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    fun editDriveComment(id: Long,
                         comment: String): Observable<Unit> {
        return service.editDriveComment(token, id, comment)
    }

    /**
     * Удаление комментария к ТС.
     *
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    fun removeDriveComment(id: Long): Observable<Unit> {
        return service.removeDriveComment(token, id)
    }
}