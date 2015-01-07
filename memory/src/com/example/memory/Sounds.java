package com.example.memory;

import android.os.Bundle;
import android.widget.CheckBox;
import android.app.Activity;
import android.content.SharedPreferences;

public class Sounds extends Activity {

	CheckBox sound;
	CheckBox music;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds);
        music=(CheckBox) findViewById(R.id.musicCheckBox);
        sound=(CheckBox) findViewById(R.id.soundCheckBox);
        SharedPreferences settings = getSharedPreferences("settings", 0);
        boolean musicActivated=settings.getBoolean("music", false);
        boolean soundActivated=settings.getBoolean("sound", false);
        music.setChecked(musicActivated);
        sound.setChecked(soundActivated);
    }

    public void onBackPressed(){
    	SharedPreferences settings = getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        boolean musicActivated=music.isChecked();
        boolean soundActivated=sound.isChecked();
        editor.putBoolean("music", musicActivated);
        editor.putBoolean("sound", soundActivated);
        editor.commit();
    	finish();
    }
    
}
