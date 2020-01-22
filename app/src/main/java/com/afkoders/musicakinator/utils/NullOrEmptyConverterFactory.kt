package com.afkoders.musicakinator.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


internal class NullOrEmptyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, Any?> {
        return object : Converter<ResponseBody, Any?> {
            val delegate = retrofit.nextResponseBodyConverter<Any?>(
                this@NullOrEmptyConverterFactory,
                type,
                annotations
            )

            override fun convert(value: ResponseBody) =
                if (value.contentLength() != 0L) delegate.convert(value) else Unit
        }
    }

}