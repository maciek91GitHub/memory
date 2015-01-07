package com.example.memory;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Newgame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
    }
    
    public void easyButtonClick(View view){
    	Intent intent = new Intent(this, GameActivity.class);
    	intent.putExtra("difficulty", 1); // easy == 1
    	startActivity(intent);
    	finish();
    }
    public void mediumButtonClick(View view){
    	Intent intent = new Intent(this, GameActivity.class);
    	intent.putExtra("difficulty", 2); // medium == 2
    	startActivity(intent);
    	finish();
    }
    public void hardButtonClick(View view){
    	Intent intent = new Intent(this, GameActivity.class);
    	intent.putExtra("difficulty", 3); // hard==3
    	startActivity(intent);
    	finish();
    }

}
