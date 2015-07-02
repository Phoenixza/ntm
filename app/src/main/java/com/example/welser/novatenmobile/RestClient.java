package com.example.welser.novatenmobile;


import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class RestClient {

    private static RestClient sInstance = null;

    public static RestClient getInstance() {
        return RestClient.getInstance();
    }

    /*public static RestClient getInstance() {
        if (sInstance == null) {
            sInstance = new RestClient();
        }

        return sInstance;
    }*/

    public void getFeed(final ResultsListener<String> aListener) {

        Api.get("feed/", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (i == 200) {
                    aListener.onSuccess(""+bytes);
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //aListener.onFailure(e);
            }
        });
    }

}