package ru.annin.autorespect.data.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.HttpException
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import ru.annin.autorespect.domain.model.ErrorResponse
import rx.Observable
import java.io.IOException
import java.lang.reflect.Type

/**
 * Фабрика поддержки RxJava. С обработкой исключений.
 *
 * @author Pavel Annin.
 */
class RxErrorHandlingAdapterFactory(
        val original: RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()) : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*>
            = RxCallAdapterWrapper(retrofit!!, original.get(returnType, annotations, retrofit))

    private class RxCallAdapterWrapper(val retrofit: Retrofit,
                                       val wrapped: CallAdapter<*>) : CallAdapter<Observable<*>> {

        override fun responseType(): Type = wrapped.responseType()

        override fun <R : Any?> adapt(call: Call<R>?): Observable<*>
                = (wrapped.adapt(call) as Observable<*>)
                .onErrorResumeNext { Observable.error(asRetrofitException(it)) }

        fun asRetrofitException(throwable: Throwable): ApiException {
            if (throwable is HttpException) {
                val model = retrofit.responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, arrayOfNulls<Annotation>(0))
                        .convert(throwable.response().errorBody())
                return ApiException(code = throwable.response().code(), errors = model)
            } else if (throwable is IOException) {
                return ApiException(isNetworkException = true)
            }
            return ApiException()
        }
    }
}