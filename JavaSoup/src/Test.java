import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws Exception {
        String payload = "{" +
                "\"size\": 0, " +
                "\"aggs\": {" +
                    "\"distinct_requests\":{" +
                        "\"cardinality\":{" +
                            "\"field\" : \"request_json.request_id.keyword\"" +
                        "}" +
                    "}" +
                "}" +
        "}";

        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_FORM_URLENCODED);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:9200/orion-changan-s1-2018.12.20/_search");
        request.setHeader("Content-Type", "application/json");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }
}