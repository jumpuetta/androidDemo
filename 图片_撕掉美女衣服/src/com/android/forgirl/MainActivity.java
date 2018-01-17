package com.android.forgirl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	  private ImageView back;
	  private ImageView up;
	  private int windowWidth;
	  private int windowHeight;
	  private Bitmap alertbitmap;
	  //������
	  private Canvas canvas;
	  //������
	  private Paint paint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		back = (ImageView)findViewById(R.id.back);
		up = (ImageView)findViewById(R.id.up);
		
		WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
		windowWidth = manager.getDefaultDisplay().getWidth();
		windowHeight = manager.getDefaultDisplay().getHeight();
        
		BitmapFactory.Options options_back = new Options();
    	options_back.inJustDecodeBounds = true;
    	Bitmap bitmap_back = BitmapFactory.decodeResource(getResources(),R.drawable.g4_back,options_back);
    	
    	//��ȡͼƬ����ʵ���
    	int imageWidth_back = options_back.outWidth;
    	int imageHeight_back = options_back.outHeight;

    	int scaleX_back = imageWidth_back / windowWidth;
    	int scaleY_back = imageHeight_back / windowHeight;
    	
    	int scale_back = scaleX_back >= scaleY_back ? scaleX_back : scaleY_back ;
    	//��Ľ���ͼƬ  false
    	options_back.inJustDecodeBounds = false;
    	options_back.inSampleSize = scale_back;
    	Bitmap smallbitmap_back = BitmapFactory.decodeResource(getResources(),R.drawable.g4_back,options_back);
    	back.setImageBitmap(smallbitmap_back);
		
    	BitmapFactory.Options options_up = new Options();
    	options_up.inJustDecodeBounds = true;
    	Bitmap  bitmap_up = BitmapFactory.decodeResource(getResources(),R.drawable.g4_up,options_up);
    	int imageWidth_up = options_up.outWidth;
    	int imageHeight_up = options_up.outHeight;

        int scaleX_up = imageWidth_up / windowWidth;
    	int scaleY_up = imageHeight_up / windowHeight;
    	
    	int scale_up = scaleX_up >= scaleY_up ? scaleY_up : scaleX_up ;
    	//��Ľ���ͼƬ  false
    	options_up.inJustDecodeBounds = false;
    	options_up.inSampleSize = scale_up;
    	Bitmap smallbitmap_up = BitmapFactory.decodeResource(getResources(),R.drawable.g4_up,options_up);
    	
    	//����һ���µĿ��޸ĵĿհ�Bitmap
    	alertbitmap = Bitmap.createBitmap(smallbitmap_up.getWidth(),smallbitmap_up.getHeight(), smallbitmap_up.getConfig());
    	System.out.println(scale_up+" "+smallbitmap_up.getWidth()+"  "+smallbitmap_up.getHeight());
    	canvas = new Canvas(alertbitmap);
    	
    	paint = new Paint();
    	//new Matrix() ����ԭͼ��С��ʾ
		canvas.drawBitmap(smallbitmap_up,new Matrix(), paint);
		up.setImageBitmap(alertbitmap);
	
		
    	up.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					break;
                case MotionEvent.ACTION_MOVE:
                	//���ϻ�ȡ��ȡ�µ�����  getX,getY��ȡ��ָ�ڿؼ��ϵ�����  getRawX,getRawY��ȡ��ָ����Ļ�ϵ�����
                	int new_x = (int)event.getX();
					int new_y = (int)event.getY();
					for(int i = -10;i <= 10;i ++){
						for(int j = -10;j <= 10;j ++){
							//�ı�ĳһ�������
					       alertbitmap.setPixel(new_x+i, new_y+j, Color.BLUE);
						}
					}
					up.setImageBitmap(alertbitmap);
					break;
                case MotionEvent.ACTION_UP:
					
					break;
				default:
					break;
				}
				return true;
			}
		});
    	
	}


}
