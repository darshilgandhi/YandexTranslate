//the purpose of this program is to send queries to Yandex API and get a response of the translation

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        Headers headers =  new Headers();
        String textToTranslate = "hello how are you";
        String desiredLang = "es";

        headers.setTargetLanguageCode(desiredLang);
        headers.setTextToTranslate(textToTranslate);


        URL url = new URL(headers.getUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine=br.readLine())!=null){
            response.append(inputLine);
        }
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        JsonNode myNode = mapper.readTree(response.toString());
        System.out.println(textToTranslate);
        System.out.println("The above text is translated below in the language code: "+desiredLang.toUpperCase()+" | Powered by Yandex.Translate");
        System.out.println(myNode.get("text").toString());


    }
}
