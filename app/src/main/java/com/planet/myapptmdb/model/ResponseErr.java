package com.planet.myapptmdb.model;

import android.util.Log;

import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ResponseErr {

    @Json(name = "status_message")
    private String statusMessage;

    @Json(name = "status_code")
    private int statusCode;

    @Json(name = "success")
    private boolean success;

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    @NotNull
    @Override
    public String toString() {
        return
                "ResponseErr{" +
                        "status_message = '" + statusMessage + '\'' +
                        ",status_code = '" + statusCode + '\'' +
                        ",success = '" + success + '\'' +
                        "}";
    }

    public static ResponseErr fromResponseBody(String responseError) {
        try {
            return new Moshi.Builder().build().adapter(ResponseErr.class)
                    .fromJson(responseError);
        } catch (IOException e) {
            Log.d("ResponseError", e.toString());
        }
        return null;
    }

}