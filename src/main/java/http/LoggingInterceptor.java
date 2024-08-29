package http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class LoggingInterceptor implements Interceptor {

    private static final int MAX_RESPONSE_BODY_LENGTH_FOR_DISPLAY = 5000;

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();

        log.info("Request URL: " + request.url().toString());
        log.info("Request Method: " + request.method());
        final RequestBody requestBody = request.body();
        if (Objects.nonNull(requestBody)) {
            log.info("Request payload: " + readRequestBody(requestBody));
        }

        final Response response = chain.proceed(request);

        log.info("Status code: " + response.code());
        final ResponseBody responseBody = response.body();
        final Response.Builder responseBuilder = response.newBuilder();
        if (Objects.nonNull(responseBody)) {
            final byte[] bytes = responseBody.bytes();
            String responseContent = new String(bytes, StandardCharsets.UTF_8);
            if (responseContent.length() <= MAX_RESPONSE_BODY_LENGTH_FOR_DISPLAY) {
                log.info("Response body: " + responseContent);
            }
            responseBuilder.body(ResponseBody.create(responseBody.contentType(), bytes));
        }

        return responseBuilder.build();
    }

    private static String readRequestBody(final RequestBody requestBody) {
        try (final Buffer buffer = new Buffer()) {
            requestBody.writeTo(buffer);
            return buffer.readString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Can not write request body to the buffer: ", e);
        }
    }
}
