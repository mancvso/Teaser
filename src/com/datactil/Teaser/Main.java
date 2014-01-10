package com.datactil.Teaser;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.VideoView;

public class Main extends Activity {
    VideoView videoView;
    ImageView logo;

    /**
     * fadein al logo y, mientras, cargar el video
     * fadeout al logo y al terminar reproducir video.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        logo = (ImageView) findViewById(R.id.logo);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1500);
        fadeIn.setRepeatCount(0);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //add this
        fadeOut.setDuration(500);
        fadeOut.setStartOffset(500);
        fadeOut.setRepeatCount(0);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                loadVideo();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //pass
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //pass
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.INVISIBLE);
                playVideo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //pass
            }
        });

        logo.startAnimation(fadeIn);

        /*final Button button = (Button) findViewById(R.id.suscribe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.datactil.com"));
                startActivity(browse);
            }
        });*/

    }

    public void loadVideo(){
        videoView =(VideoView)findViewById(R.id.video);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.paquito ));
        //loop
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    public void playVideo(){
        videoView.setVisibility(View.VISIBLE);
        videoView.start();
        videoView.requestFocus();
    }
}
