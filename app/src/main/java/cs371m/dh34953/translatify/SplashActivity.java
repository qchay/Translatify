package cs371m.dh34953.translatify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView welcomeLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        welcomeLogo =findViewById(R.id.logo);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.transition);
        welcomeLogo.startAnimation(animation);
        final Intent intent = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }

}
