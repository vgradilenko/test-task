package http;

import config.ConfigProvider;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestApiProvider {

    public static <S> S getRestApi(Class<S> serviceClass, String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Duration timeout = Duration.ofSeconds(ConfigProvider.CONFIG_PROPS.getConnectionTimeout());
        final OkHttpClient client = builder
                .connectTimeout(timeout)
                .readTimeout(timeout)
                .writeTimeout(timeout)
                .addInterceptor(new AllureOkHttp3())
                .addInterceptor(new LoggingInterceptor())
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass);
    }
}