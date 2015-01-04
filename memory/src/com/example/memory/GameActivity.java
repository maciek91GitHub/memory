package com.example.memory;

import java.util.Random;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class DrawView extends View {
	Paint paint = new Paint();
	int diff;
	Bitmap[] obrazki = new Bitmap[16];
	int[][] tab;
	boolean[][] odkryta;
	int maxX;
	int maxY;
	int maxW;
	public DrawView(Context context) {
		super(context);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		maxW = canvas.getWidth();
		int imgWidth = ((maxW - (10 * (maxX + 1))) / maxX);
		for (int i = 0; i < 16; i++) {
			obrazki[i] = obrazki[i].createScaledBitmap(obrazki[i], imgWidth, imgWidth, false);
		}
	
		int cordY = 10;
		int cordX = 10;
		paint.setColor(Color.BLACK);
		for (int i = 0; i < maxY; i++) {
			for (int j = 0; j < maxX; j++) {
				canvas.drawRect(cordX - 2, cordY - 2, cordX + imgWidth + 2, cordY + imgWidth + 2, paint);
				if (odkryta[i][j]) {
					canvas.drawBitmap(obrazki[tab[i][j]], cordX, cordY, paint);
				} else {
					canvas.drawBitmap(obrazki[15], cordX, cordY, paint);
				}
				cordX = cordX + imgWidth + 10;
			}
			cordY = cordY + imgWidth + 10;
			cordX = 10;
		}
		String ttt = "maxX " + maxX + " " + maxY + " " + GameActivity.ileOdkrytych;
		canvas.drawText(ttt, 20, 400, paint);
		
	}
	
}

public class GameActivity extends Activity implements OnTouchListener {
	
	DrawView drawView;
	Random randomizer = new Random();
	int maxX;
	int maxY;
	static int ileOdkrytych=0;
	boolean czyDwie=false;
	int paraX,paraY;
	
	public void ustaw(int[][] tab, boolean[][] o) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				o[i][j] = true;
				
			}
		}
		
		for (int i = 0; i < ((maxX * maxY) / 2); i++) {
			boolean nieZnaleziono1 = true;
			
			while (nieZnaleziono1) {
				int x1 = randomizer.nextInt(maxX);
				int y1 = randomizer.nextInt(maxY);
				if (o[y1][x1]) {
					tab[y1][x1] = i;
					o[y1][x1] = false;
					nieZnaleziono1 = false;
					boolean nieZnaleziono2 = true;
					while (nieZnaleziono2) {
						int x2 = randomizer.nextInt(maxX);
						int y2 = randomizer.nextInt(maxY);
						if (o[y2][x2]) {
							tab[y2][x2] = i;
							o[y2][x2] = false;
							nieZnaleziono2 = false;
						}
					}
				}
			}
		}
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		
		int imgWidth = ((drawView.maxW - (10 * (maxX + 1))) / maxX);
		int x=(int) event.getX();
		int y=(int) event.getY();
		int imgX=-1;
		int imgY=-1;
		
		for(int i=0;i<maxX;i++){
			if((x>=(10*(i+1)+imgWidth*i))&& (x<=(10*(i+1)+imgWidth*(i+1)))){
				imgX=i;
			}
		}
		for(int i=0;i<maxY;i++){
			if((y>=(10*(i+1)+imgWidth*i))&& (y<=(10*(i+1)+imgWidth*(i+1)))){
				imgY=i;
			}
		}
		if((imgX!=-1)&&(imgY!=-1)){
			if(!drawView.odkryta[imgY][imgX]){
			
				ileOdkrytych++;
				if(ileOdkrytych==(maxX*maxY)){
					android.os.Process.killProcess(android.os.Process.myPid());
				}
				
				drawView.odkryta[imgY][imgX]=true;
				drawView.invalidate();
				
				if(!czyDwie){
					paraX=imgX;
					paraY=imgY;
				}
				if(czyDwie){
					if(drawView.tab[imgY][imgX]!=drawView.tab[paraY][paraX]){
						drawView.odkryta[imgY][imgX]=false;
						drawView.odkryta[paraY][paraX]=false;
						ileOdkrytych=ileOdkrytych-2;
					}
				}
				czyDwie=!czyDwie;
				
			}
		}
		return true;
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Intent intent = getIntent();
		int difficulty = intent.getIntExtra("difficulty", 6);
		drawView = new DrawView(this);
		drawView.setBackgroundColor(Color.MAGENTA);
		drawView.diff = difficulty;
		
		switch (difficulty) {
		case 1:
			maxX = 4;
			maxY = 4;
			drawView.maxX=maxX;
			drawView.maxY=maxY;
			drawView.tab = new int[maxY][maxX];
			drawView.odkryta = new boolean[maxY][maxX];
			ustaw(drawView.tab, drawView.odkryta);
			break;
		case 2:
			maxX = 4;
			maxY = 5;
			drawView.maxX=maxX;
			drawView.maxY=maxY;
			drawView.tab = new int[maxY][maxX];
			drawView.odkryta = new boolean[maxY][maxX];
			ustaw(drawView.tab, drawView.odkryta);
			break;
		case 3:
			maxX = 5;
			maxY = 6;
			drawView.maxX=maxX;
			drawView.maxY=maxY;
			drawView.tab = new int[maxY][maxX];
			drawView.odkryta = new boolean[maxY][maxX];
			ustaw(drawView.tab, drawView.odkryta);
			break;
		default:
		}
		
		Resources res = getResources();
		drawView.obrazki[0] = BitmapFactory
				.decodeResource(res, R.drawable.img1);
		drawView.obrazki[1] = BitmapFactory
				.decodeResource(res, R.drawable.img2);
		drawView.obrazki[2] = BitmapFactory
				.decodeResource(res, R.drawable.img3);
		drawView.obrazki[3] = BitmapFactory
				.decodeResource(res, R.drawable.img4);
		drawView.obrazki[4] = BitmapFactory
				.decodeResource(res, R.drawable.img5);
		drawView.obrazki[5] = BitmapFactory
				.decodeResource(res, R.drawable.img6);
		drawView.obrazki[6] = BitmapFactory
				.decodeResource(res, R.drawable.img7);
		drawView.obrazki[7] = BitmapFactory
				.decodeResource(res, R.drawable.img8);
		drawView.obrazki[8] = BitmapFactory
				.decodeResource(res, R.drawable.img9);
		drawView.obrazki[9] = BitmapFactory.decodeResource(res,
				R.drawable.img10);
		drawView.obrazki[10] = BitmapFactory.decodeResource(res,
				R.drawable.img11);
		drawView.obrazki[11] = BitmapFactory.decodeResource(res,
				R.drawable.img12);
		drawView.obrazki[12] = BitmapFactory.decodeResource(res,
				R.drawable.img13);
		drawView.obrazki[13] = BitmapFactory.decodeResource(res,
				R.drawable.img14);
		drawView.obrazki[14] = BitmapFactory.decodeResource(res,
				R.drawable.img15);
		drawView.obrazki[15] = BitmapFactory.decodeResource(res,
				R.drawable.hide);
		drawView.setOnTouchListener(this);
		setContentView(drawView);
		
		//drawView.odkryta[1][1]=true;
		//drawView.invalidate();
	}
}
