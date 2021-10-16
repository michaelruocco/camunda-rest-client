package uk.co.mruoc.camunda.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.json.JsonConverter;

import java.net.http.HttpResponse;

@Slf4j
@RequiredArgsConstructor
public class ResponseConverter {

    private final JsonConverter jsonConverter;

    public <T> T toTypeOrThrowError(HttpResponse<String> response, Class<T> type) {
        String body = response.body();
        if (isSuccessful(response)) {
            log.info(body);
            return jsonConverter.toObject(body, type);
        }
        throw new CamundaClientException(body);
    }

    private boolean isSuccessful(HttpResponse<String> response) {
        var status = response.statusCode();
        return status >= 200 && status <= 299;
    }

}
