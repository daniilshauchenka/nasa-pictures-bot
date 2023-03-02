package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpManager {

    private ObjectMapper mapper = null;
    private final CloseableHttpClient httpClient;

    public ObjectMapper getMapper() {
        return mapper;
    }


    public HttpManager() {
        this.mapper = new ObjectMapper();
        this.httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
    }

    public CloseableHttpResponse executeRequest(String uri) throws IOException {
        return httpClient.execute(new HttpGet(uri));
    }

    public String getPictureUrl(String uri){
        return "";
    }

}
