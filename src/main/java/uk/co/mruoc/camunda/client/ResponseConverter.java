package uk.co.mruoc.camunda.client;

import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.json.JsonConverter;

@Slf4j
@RequiredArgsConstructor
public class ResponseConverter {

    private final JsonConverter jsonConverter;

    public void throwErrorIfRequired(HttpResponse<String> response) {
        log(response);
        if (!isSuccessful(response)) {
            throw new CamundaClientException(response.body());
        }
    }

    public <T> T toTypeOrThrowError(HttpResponse<String> response, Class<T> type) {
        log(response);
        String body = response.body();
        if (isSuccessful(response)) {
            return jsonConverter.toObject(body, type);
        }
        throw new CamundaClientException(body);
    }

    private void log(HttpResponse<String> response) {
        log.info("response status: {} body: {}", response.statusCode(), response.body());
    }

    private boolean isSuccessful(HttpResponse<String> response) {
        var status = response.statusCode();
        log.info("status: " + status);
        return status >= 200 && status <= 299;
    }
}
