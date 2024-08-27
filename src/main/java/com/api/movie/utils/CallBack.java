package com.api.movie.utils;

public interface CallBack<T> {
    void onSuccess(T value);

    void onFailure(Exception e);
}
