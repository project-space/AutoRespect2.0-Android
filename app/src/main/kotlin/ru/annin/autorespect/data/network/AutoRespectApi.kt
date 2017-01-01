package ru.annin.autorespect.data.network

import retrofit2.http.*
import ru.annin.autorespect.domain.model.CommentResponse
import ru.annin.autorespect.domain.model.DriverInfoResponse
import ru.annin.autorespect.domain.model.DriverRatingResponse
import ru.annin.autorespect.domain.model.TokenResponse
import rx.Observable

/**
 * Rest API.
 *
 * @author Pavel Annin.
 */
interface AutoRespectApi {

    /**
     * Регистрация нового пользователя.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     * @param userName Имя пользователя.
     */
    @POST("/api/v1/auth/registration")
    fun registration(@Field("login") login: String,
                     @Field("password") password: String,
                     @Field("userName") userName: String): Observable<TokenResponse>

    /**
     * Авторизация.
     *
     * @param login Логин пользователя.
     * @param password Пароль пользователя.
     */
    @GET("/api/v1/auth/authorization")
    fun authorization(@Query("login") login: String,
                      @Query("password") password: String): Observable<TokenResponse>

    /**
     * Информация о водителе.
     *
     * @param token Пользовательский токен.
     * @param regionCode Код региона.
     * @param numberCode Номер ТС.
     */
    @GET("/api/v1/driver/{regionCode}/{numberCode}")
    fun getDriverInfo(@Header("Auth-Token") token: String,
                      @Path("regionCode") regionCode: Int,
                      @Path("numberCode") numberCode: String): Observable<DriverInfoResponse>

    /**
     * Оценить водителя.
     *
     * @param token Пользовательский токен.
     * @param id Идентификатор ТС.
     * @param rating Пользовательская оценка, возможные значения: Like (1) / Dislike (-1) / Undefined(0).
     */
    @POST("/api/v1/driver/rating/{id}")
    fun sendDriverRating(@Header("Auth-Token") token: String,
                         @Path("id") id: Long,
                         @Field("rating") rating: Int): Observable<DriverRatingResponse>

    /**
     * Комментарии к ТС.
     *
     * @param token Пользовательский токен.
     * @param id Идентификатор ТС.
     * @param limit Количество комментариев в ответе.
     * @param offset Смещение комментариев.
     */
    @GET("/api/v1/driver/comment/{id}")
    fun getDriveComments(@Header("Auth-Token") token: String,
                         @Path("id") id: Long,
                         @Query("limit") limit: Int,
                         @Query("offset") offset: Int): Observable<List<CommentResponse>>

    /**
     * Создание комментария к ТС.
     *
     * @param token Пользовательский токен.
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    @POST("/api/v1/driver/comment/{id}")
    fun createDriveComment(@Header("Auth-Token") token: String,
                           @Path("id") id: Long,
                           @Field("comment") comment: String): Observable<Unit>

    /**
     * Изменение комментария к ТС.
     *
     * @param token Пользовательский токен.
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    @PUT("/api/v1/driver/comment/{id}")
    fun editDriveComment(@Header("Auth-Token") token: String,
                         @Path("id") id: Long,
                         @Field("comment") comment: String): Observable<Unit>

    /**
     * Удаление комментария к ТС.
     *
     * @param token Пользовательский токен.
     * @param id Идентификатор ТС.
     * @param comment Текст комментария.
     */
    @DELETE("/api/v1/driver/comment/{id}")
    fun removeDriveComment(@Header("Auth-Token") token: String,
                           @Path("id") id: Long): Observable<Unit>
}