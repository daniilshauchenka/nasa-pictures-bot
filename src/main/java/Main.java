import com.fasterxml.jackson.databind.ObjectMapper;
import entity.NasaObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=XBUnOudzrgaIXFfTr2rI4Ofmfnmj2VgsaynHu2b2";

    public static final ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        CloseableHttpResponse response = httpClient.execute(new HttpGet(URI));

        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(), NasaObject.class);
        System.out.println(nasaObject);
        CloseableHttpResponse pictureResponse = httpClient.execute(new HttpGet(nasaObject.getPictureUrl()));
        String[] nasaObjectNameArray = nasaObject.getPictureUrl().split("/");
        String fileName = nasaObjectNameArray[nasaObjectNameArray.length-1];
        HttpEntity httpEntity = pictureResponse.getEntity();
        if(httpEntity != null){
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            httpEntity.writeTo(fileOutputStream);
            fileOutputStream.close();
        }


    }
}
