package com.example.memory;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void newGameClick(View view){
    	Intent intent = new Intent(this, Newgame.class);
    	startActivity(intent);
    }
    public void hiscoresClick(View view){
    	Intent intent = new Intent(this, Hiscores.class);
    	startActivity(intent);
    }
    public void soundsClick(View view){
    	Intent intent = new Intent(this, Sounds.class);
    	startActivity(intent);

    }
    public void aboutClick(View view){
    	Intent intent = new Intent(this, About.class);
    	startActivity(intent);
    	
    }
    public void exitClick(View view){
    	android.os.Process.killProcess(android.os.Process.myPid());
    }
}
