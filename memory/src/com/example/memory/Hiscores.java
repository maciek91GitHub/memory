package com.example.memory;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.Activity;

public class Hiscores extends Activity {

	TabHost tabs;
	TabSpec tspec1;
	TabSpec tspec2;
	TabSpec tspec3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscores);
        
        tabs = (TabHost) this.findViewById(R.id.my_tabhost);
        tabs.setup();
        tspec1 = tabs.newTabSpec("tab1");      
        tspec1.setIndicator("Easy");
        tspec1.setContent(R.id.tab1);
        tabs.addTab(tspec1);
        tspec2 = tabs.newTabSpec("tab2");
        tspec2.setIndicator("Medium");
        tspec2.setContent(R.id.tab2);
        tabs.addTab(tspec2);
        tspec3 = tabs.newTabSpec("tab3");
        tspec3.setIndicator("Hard");
        tspec3.setContent(R.id.tab3);
        tabs.addTab(tspec3);

    }

}
