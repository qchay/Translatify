package cs371m.dh34953.translatify;

// Imports the Google Cloud client library
import android.os.AsyncTask;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

class Translator extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate = null;
    private String API_KEY="";

    protected String doInBackground(String... params) {

        String text = params[0]; //text to translate
        String targetLanguage = params[1]; //text to translate

        TranslateOptions options = TranslateOptions.newBuilder()
                .setApiKey(API_KEY)
                .build();
        Translate translate = options.getService();
        Translation translation = translate.translate(text,Translate.TranslateOption.targetLanguage(targetLanguage));
        return translation.getTranslatedText();
    }

    //this method will run after doInBackground execution
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}