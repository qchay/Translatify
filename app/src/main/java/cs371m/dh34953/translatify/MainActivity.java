package cs371m.dh34953.translatify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AsyncResponse {

    String textToTranslate;
    EditText input,result;
    Button translateButton,clipboardTop,clipboardBottom;
    FloatingActionButton startButton;
    Translator translator;
    Spinner languageSpinner;

    Map<String,String > languageMap=new HashMap<>();
    String targetLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        languageSpinner=findViewById(R.id.spinner);
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetLanguage=languageMap.get(languageSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        startButton = findViewById(R.id.startButton);
        translateButton = findViewById(R.id.translateButton);
        clipboardTop=findViewById(R.id.clipboardTop);
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(250); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(1); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        clipboardTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null,input.getText());
                clipboard.setPrimaryClip(clip);
                clipboardTop.startAnimation(animation);
            }
        });
        clipboardBottom=findViewById(R.id.clipboardBottom);
        clipboardBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null,result.getText());
                clipboard.setPrimaryClip(clip);
                clipboardBottom.startAnimation(animation);

            }
        });
        input = findViewById(R.id.input);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textToTranslate=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        result = findViewById(R.id.result);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraActivity = new Intent(getApplicationContext(), CameraActivity.class);
                startActivityForResult(cameraActivity, 1);
            }
        });
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textToTranslate!=null) {
                    translator = new Translator();
                    translator.delegate = MainActivity.this;
                    String[] params = {textToTranslate, targetLanguage};
                    translator.execute(params);
                }
            }
        });
        mapLanguageCode(R.array.Languages,R.array.code);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return true;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                textToTranslate = data.getStringExtra("translatedText");
                input.setText(textToTranslate);
            }
        }
    }

    @Override
    public void processFinish(String output) {
        result.setText(output);
    }

    private void mapLanguageCode(int language,int code){
        String[] languageString = getResources().getStringArray(language);
        String[] codeString = getResources().getStringArray(code);
        for(int i=0;i<languageString.length;i++){
            languageMap.put(languageString[i],codeString[i]);
        }
    }
}
