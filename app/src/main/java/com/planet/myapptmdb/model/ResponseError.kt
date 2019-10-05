package com.planet.myapptmdb.model

import android.util.Log
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import java.io.IOException

class ResponseError {

    @Json(name = "status_message")
    var statusMessage: String? = null

    @Json(name = "status_code")
    var statusCode: Int = 0

    @Json(name = "success")
    var isSuccess: Boolean = false

    override fun toString(): String {
        return "ResponseError{" +
                "status_message = '" + statusMessage + '\''.toString() +
                ",status_code = '" + statusCode + '\''.toString() +
                ",success = '" + isSuccess + '\''.toString() +
                "}"
    }

    companion object {
        fun fromResponseBody(responseError: String): ResponseError? {
            try {
                return Moshi.Builder().build().adapter(ResponseError::class.java)
                    .fromJson(responseError)
            } catch (e: IOException) {
                Log.d("ResponseError", e.toString())
            }
            return ResponseError()
        }
    }
}