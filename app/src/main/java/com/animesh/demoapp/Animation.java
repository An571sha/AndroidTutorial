package com.animesh.demoapp;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Animation extends AppCompatActivity {
    ImageView batman;
    ImageView superman;
    ImageView wonderWoman;


    TransitionDrawable td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        superman = findViewById(R.id.imageView1);
        batman = findViewById(R.id.imageView2);
        wonderWoman = findViewById(R.id.imageView3);
        superman.setTranslationX(-2000f);

       /* td = new TransitionDrawable( new Drawable[]{
                getResources().getDrawable(R.drawable.superman),
                getResources().getDrawable(R.drawable.batman),
                getResources().getDrawable(R.drawable.wonderwoman)
        });
        superman.setImageDrawable(td);
        td.startTransition(2000);
        */
    }


    public void fadeS(View view){


       // td.reverseTransition(2000);
        superman.animate().translationXBy(2000f).setDuration(1000);


        /*
        superman.animate().alpha(0f).setDuration(2000);
        batman.animate().alpha(1f).setDuration(2000);
        batman.animate().alpha(0f).setDuration(2000);
        superman.animate().alpha(1f).setDuration(2000);
        */
    }

   /* public void fadeB(View view){


    }*/
}

