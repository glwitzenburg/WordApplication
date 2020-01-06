package sample;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.BHttpConnectionBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONValue;

import java.io.IOException;

public class Translator {

    static String key = "trnsl.1.1.20191030T172530Z.c1f316ec9184c847.15413e5720eb004f570166f8f9d9a04c1b219f47";
    public String wordToTranslate;
    public String translateFrom;
    public String translateTo;

    public static String sendThisWord;
    public static String sendThisFrom;
    public static String sendThisTo;

    public String getWord() {
        return this.wordToTranslate;
    }
    public void setWord(String word) {
        this.wordToTranslate = word;
        sendThisWord = word;
    }

    public String getFrom(){
        return  translateFrom;
    }
    public void setFrom(String setFrom){
        this.translateFrom = setFrom;
        sendThisFrom = setFrom;
    }

    public String getTo(){
        return translateTo;
    }
    public void setTo(String setTo){
        this.translateTo = setTo;
        sendThisTo = setTo;
    }

    public void sendRequest() throws IOException {
        //JSONValue jv = (JSONValue)sendThisWord;
        String urlRequest = ("https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&"+"text="+sendThisWord+"&"+"lang="+sendThisFrom+"-"+sendThisTo);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlRequest);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        String responseToBeReturned = org.apache.http.util.EntityUtils.toString(response1.getEntity());

        System.out.println(responseToBeReturned);
    }
}
