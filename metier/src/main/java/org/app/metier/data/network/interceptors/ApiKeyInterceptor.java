package org.app.metier.data.network.interceptors;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    private String apiKey;
    private String API_KEY_QUERY_PARAMETER = "api_key";

    public ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();

        HttpUrl urlWithApiKey = originalUrl.newBuilder()
                .addQueryParameter(API_KEY_QUERY_PARAMETER, "01643e091b663b8a7d9dc90188c60cd0")
                .build();

        Request modifiedRequest = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build();

        okhttp3.Response response = chain.proceed(modifiedRequest);

        return response;
    }
}