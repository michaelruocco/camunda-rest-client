package uk.co.mruoc.camunda.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.http.HttpResponse;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.json.JsonConverter;

class ResponseConverterTest {

    private static final String BODY = "response-body";

    private final JsonConverter jsonConverter = mock(JsonConverter.class);

    private final ResponseConverter responseConverter = new ResponseConverter(jsonConverter);

    @Test
    void shouldConvertBodyToRequiredTypeIfStatusCodeIs200() {
        Object expectedResponse = new Object();
        HttpResponse<String> httpResponse = givenResponse(200);
        givenResponseBodyConvertsTo(expectedResponse, Object.class);

        Object response = responseConverter.toTypeOrThrowError(httpResponse, Object.class);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    void shouldReturnVerificationIfStatusCodeIs299() {
        Object expectedResponse = new Object();
        HttpResponse<String> httpResponse = givenResponse(299);
        givenResponseBodyConvertsTo(expectedResponse, Object.class);

        Object response = responseConverter.toTypeOrThrowError(httpResponse, Object.class);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    void shouldDoNothingIfStatusCodeIs200() {
        HttpResponse<String> httpResponse = givenResponse(200);

        ThrowingCallable call = () -> responseConverter.throwErrorIfRequired(httpResponse);

        assertThatCode(call).doesNotThrowAnyException();
    }

    @Test
    void shouldDoNothingIfStatusCodeIs299() {
        HttpResponse<String> httpResponse = givenResponse(299);

        ThrowingCallable call = () -> responseConverter.throwErrorIfRequired(httpResponse);

        assertThatCode(call).doesNotThrowAnyException();
    }

    @Test
    void shouldThrowExceptionIfStatusCodeIsBelow2XXRange() {
        HttpResponse<String> response = givenResponse(199);

        Throwable error = catchThrowable(() -> responseConverter.toTypeOrThrowError(response, Object.class));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasMessage(BODY);
    }

    @Test
    void shouldThrowExceptionIfStatusCodeIsAbove2XXRange() {
        HttpResponse<String> response = givenResponse(300);

        Throwable error = catchThrowable(() -> responseConverter.toTypeOrThrowError(response, Object.class));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasMessage(BODY);
    }

    @Test
    void shouldThrowExceptionIfStatusCodeIsBelow2XXRangeWithNoType() {
        HttpResponse<String> response = givenResponse(199);

        Throwable error = catchThrowable(() -> responseConverter.throwErrorIfRequired(response));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasMessage(BODY);
    }

    @Test
    void shouldThrowExceptionIfStatusCodeIsAbove2XXRangeWithNoType() {
        HttpResponse<String> response = givenResponse(300);

        Throwable error = catchThrowable(() -> responseConverter.throwErrorIfRequired(response));

        assertThat(error).isInstanceOf(CamundaClientException.class).hasMessage(BODY);
    }

    private HttpResponse<String> givenResponse(int statusCode) {
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.body()).thenReturn(BODY);
        when(response.statusCode()).thenReturn(statusCode);
        return response;
    }

    private <T> void givenResponseBodyConvertsTo(T object, Class<T> type) {
        when(jsonConverter.toObject(BODY, type)).thenReturn(object);
    }
}
