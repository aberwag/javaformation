package gesterreurs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;

public class ClientException extends HttpStatusCodeException {

    private static final long serialVersionUID = 1L;

    public ClientException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public ClientException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody,
            Charset responseCharset) {

        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }

    @Override
    public String toString() {
        return "ClientException [statutCode=" + getStatusCode() + ", message=" + getStatusText() + "]";
    }
}