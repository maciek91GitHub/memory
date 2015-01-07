package com.example.memory;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;

public class Hiscores extends Activity {

	TabHost tabs;
	TabSpec tspec1;
	TabSpec tspec2;
	TabSpec tspec3;
	List<String> tab1 = new ArrayList<String>();
	List<String> tab2 = new ArrayList<String>();
	List<String> tab3 = new ArrayList<String>();
    SharedPreferences settings ;
    
    public void setTabsByDifficulty(int difficulty){
    	tab1.clear();
    	tab2.clear();
    	tab3.clear();
    	for(int i=1;i<=3;i++){
        	String key1=""+difficulty+i+1;
        	String key2=""+difficulty+i+2;
        	String key3=""+difficulty+i+3;
        	String tempName=settings.getString(key1, "");
        	int tempTime=settings.getInt(key2, 0);
        	int tempMov=settings.getInt(key3, 0);
        	tab1.add(tempName);
        	if(tempTime>0){
        		tab2.add("  T:"+tempTime);
        	}
        	else{
        		tab2.add("");
        	}
        	if(tempMov>0){
        		tab3.add("  MM:"+tempMov);
        	}
        	else{
        		tab3.add("");
        	}
        	
       }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscores);
        settings = getSharedPreferences("hiscores", 0);
        
        setTabsByDifficulty(1);
        TextView easyFirst=(TextView)findViewById(R.id.easyFirst);
        TextView easySecond=(TextView)findViewById(R.id.easySecond);
        TextView easyThird=(TextView)findViewById(R.id.easyThird);
        easyFirst.setText("1. "+tab1.get(0)+tab2.get(0)+tab3.get(0));
        easySecond.setText("2. "+tab1.get(1)+tab2.get(1)+tab3.get(1));
        easyThird.setText("3. "+tab1.get(2)+tab2.get(2)+tab3.get(2));
        
        setTabsByDifficulty(2);
        TextView mediumFirst=(TextView)findViewById(R.id.mediumFirst);
        TextView mediumSecond=(TextView)findViewById(R.id.mediumSecond);
        TextView mediumThird=(TextView)findViewById(R.id.mediumThird);
        mediumFirst.setText("1. "+tab1.get(0)+tab2.get(0)+tab3.get(0));
        mediumSecond.setText("2. "+tab1.get(1)+tab2.get(1)+tab3.get(1));
        mediumThird.setText("3. "+tab1.get(2)+tab2.get(2)+tab3.get(2));
        
        setTabsByDifficulty(3);
        TextView hardFirst=(TextView)findViewById(R.id.hardFirst);
        TextView hardSecond=(TextView)findViewById(R.id.hardSecond);
        TextView hardThird=(TextView)findViewById(R.id.hardThird);
        hardFirst.setText("1. "+tab1.get(0)+tab2.get(0)+tab3.get(0));
        hardSecond.setText("2. "+tab1.get(1)+tab2.get(1)+tab3.get(1));
        hardThird.setText("3. "+tab1.get(2)+tab2.get(2)+tab3.get(2));
        
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
