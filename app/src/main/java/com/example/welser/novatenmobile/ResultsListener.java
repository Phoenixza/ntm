package com.example.welser.novatenmobile;

/**
 * Created by welser on 23.06.2015.
 */
public interface ResultsListener<T> {

    public void onSuccess(T result);
    public void onFailure(Throwable e);

}