package me.shaohsiung.job;

import me.shaohsiung.util.HTTPUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

public class EudbJob extends WordJob {
    private String dictionaryId;
    
    private String accessToken;
    
    public EudbJob(String key, String url) {
        super(key, url);
    }

    @Override
    public HttpUriRequest prepareRequest(String word) {
        final HttpPost httPost = new HttpPost(url);
        httPost.setHeader(HTTP.USER_AGENT, HTTPUtils.userAgent());
        httPost.setHeader("Authorization", accessToken);
        httPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        
        String body = "{\n" +
                "    \"id\": \""+  dictionaryId +"\",\n" +
                "    \"language\": \"en\",\n" +
                "    \"words\": [\n" +
                "        \"" + word + "\"\n" +
                "    ]\n" +
                "}";
        HttpEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httPost.setEntity(stringEntity);
        return httPost;
    }

    @Override
    public void handleResponse(String body) {
        
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}