import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Headers {


        private String api_key = System.getenv("api_key");
        String textToTranslate;
        String targetLanguageCode;
        String baseUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";

        List<String> langList = new ArrayList<>();
        String[] supportedLanguages = {"sq","am", "en", "ar", "hy", "af", "eu", "ba", "be", "bn", "my" ,"bg", "bs", "cy", "hu", "vi", "ht", "gl", "nl", "mrj", "el", "ka", "gu", "da", "he", "yi", "id", "ga", "it", "is", "es", "kk", "kn", "ca", "ky", "zh", "ko", "xh", "km", "lo", "la", "lv", "lt", "lb", "mg", "ms","ml","mt","mk","mi","mr","mhr","mn","de","ne","no","pa","pap","fa","pl", "pt", "ro", "ru", "ceb","sr", "si","sk","sl","sw","su", "tg", "th", "tl", "ta", "tt", "te", "tr", "udm", "uz", "uk", "ur", "fi", "fr", "hi", "hr", "cs", "sv", "gd", "et", "eo", "jv" , "ja" , "ms"};


    public Headers() {
        for(String a:supportedLanguages){
            langList.add(a);
        }

    }

    public void setTextToTranslate(String textToTranslate) throws Exception {
        this.textToTranslate = URLEncoder.encode(textToTranslate,"UTF-8");
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        if(langList.contains(targetLanguageCode)){
            this.targetLanguageCode = targetLanguageCode;
        }
        else{
            System.out.println("Unable to find that langauge code, switching to English");
            this.targetLanguageCode = "en";
        }

    }

    public String getUrl() {
        return baseUrl+api_key+"&text="+this.textToTranslate+"&lang="+this.targetLanguageCode;
    }
}
