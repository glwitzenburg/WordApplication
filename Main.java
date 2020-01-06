package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main extends Application {

    static Scanner sc = new Scanner(System.in);
    public static String initalWord = "";
    static String s = "";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {
        //launch(args);

        //System.out.println("Please enter the word you would like to see the synonym for : ");
        int counter = 0;
        if(counter < 1) {
            initalWord = sc.nextLine();
            counter++;
        }
        System.out.println("\n");
        //connectionToURL(initalWord);
        System.out.println("Enter a word or phrase you want translated: ");
        hitTranslationUrl();
    }

    public static void hitTranslationUrl() throws IOException {
        String wordToSendToTranslate = initalWord.replace(' ','+');
        Translator translator = new Translator();
        translator.setWord(wordToSendToTranslate);
        translator.setFrom("en");
        translator.setTo("es");
        translator.sendRequest();
    }

    public static void connectionToURL(String wordToFind) throws IOException, ParseException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://words.bighugelabs.com/api/2/89373e8da7a1cfc5b9c2e0e28160702d/" + wordToFind + "/json");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        String responseToBeReturned = org.apache.http.util.EntityUtils.toString(response1.getEntity());
        iterateThroughJson(responseToBeReturned);
        }

        public static void iterateThroughJson(String inputJson) throws ParseException {
            Object obj = new JSONParser().parse(inputJson);
            JSONObject jo = (JSONObject)obj;
            //nouns
            if(jo.containsKey("noun")){
                JSONObject joVerb = (JSONObject) jo.get("noun");
                if(joVerb.containsKey("syn")){
                    JSONArray jaRay = (JSONArray)joVerb.get("syn");
                    int rayCounter = 0;
                    System.out.println("Synonyms for the noun" );
                    while(jaRay.size() > rayCounter){
                        System.out.println(" - " + jaRay.get(rayCounter));
                        rayCounter++;
                    }
                }
                if(joVerb.containsKey("ant")){
                    JSONArray jaRay = (JSONArray)joVerb.get("ant");
                    int rayCounter = 0;
                    System.out.println("Antonyms for the noun");
                    while(jaRay.size() > rayCounter){
                        System.out.println(jaRay.get(rayCounter));
                        rayCounter++;
                    }
                }
            }
            System.out.println("\n");
            //verbs
            if(jo.containsKey("verb")){
                JSONObject joVerb = (JSONObject) jo.get("verb");
                if(joVerb.containsKey("syn")){
                    JSONArray jaRay = (JSONArray)joVerb.get("syn");
                    int rayCounter = 0;
                    System.out.println("Synonyms for the verb" );
                    while(jaRay.size() > rayCounter){
                        System.out.println(" - " + jaRay.get(rayCounter));
                        rayCounter++;
                    }
                    System.out.println("\n");
                }
                if(joVerb.containsKey("ant")){
                    JSONArray jaRay = (JSONArray)joVerb.get("ant");
                    int rayCounter = 0;
                    System.out.println("Antonyms for the verb");
                    while(jaRay.size() > rayCounter){
                        System.out.println(jaRay.get(rayCounter));
                        rayCounter++;
                    }
                }
            }

        }
}
