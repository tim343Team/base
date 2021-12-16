package tim.com.libnetwork.view.slideList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import tim.com.libnetwork.R;
import tim.com.libnetwork.utils.ResolutionUtil;


public class SlideBar extends androidx.appcompat.widget.AppCompatButton {
	  
    public interface OnTouchAssortListener{  
        public void onTouchAssortListener(String s);  
    }  
    
    // ����  
    private static final String[] ASSORT_TEXT = {"A", "B", "C", "D", "E", "F", "G",  
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",  
            "U", "V", "W", "X", "Y", "Z" ,"#"}; 
  
    private Paint mPaint = new Paint();  
    private int mSelectIndex = -1;  
    private OnTouchAssortListener mListener = null;
    private Activity mAttachActivity;
    PopupWindow mPopupWindow = null;
    View layoutView;
	TextView text;
    
    public SlideBar(Context context){  
        this(context,null);  
    }  
  
    public SlideBar(Context context, AttributeSet attrs) {  
        this(context, attrs,0);  
    }  
  
    public SlideBar(Context context, AttributeSet attrs, int defStyle){  
        super(context, attrs, defStyle);
        mAttachActivity = (Activity)context;
        init(context);
    }   
    private void init(Context context) {
    	layoutView = LayoutInflater.from(context).inflate(R.layout.alert_dialog_menu_layout, null);
    	text = (TextView) layoutView.findViewById(R.id.content);
	}
    
    public void setOnTouchAssortListener(OnTouchAssortListener listener) {
		this.mListener = listener;
	}

    @Override  
    protected void onDraw(Canvas canvas){  
        super.onDraw(canvas);
        int nHeight = getHeight();  
        int hWidth = getWidth();  
        int nAssortCount = ASSORT_TEXT.length;
        int nInterval = nHeight / nAssortCount; 
  
        for (int i = 0; i < nAssortCount; i++){  
        	mPaint.setAntiAlias(true);  // �����   
        	mPaint.setTypeface(Typeface.DEFAULT_BOLD);  // Ĭ�ϴ��� 
        	mPaint.setColor(Color.parseColor("#5f5f5f"));  // ��ɫ  
            if (i == mSelectIndex){  
                // ��ѡ�����ĸ�ı���ɫ�ʹ���  
            	mPaint.setColor(Color.parseColor("#3399ff"));  
            	mPaint.setFakeBoldText(true);  
            	mPaint.setTextSize(ResolutionUtil.dip2px(getContext(),12));
            }  
            float xPos = hWidth / 2 - mPaint.measureText(ASSORT_TEXT[i]) / 2;  // ������ĸ��X����  
            float yPos = nInterval * i + nInterval;  // ������ĸ��Y����  
            canvas.drawText(ASSORT_TEXT[i], xPos, yPos, mPaint);  
            mPaint.reset();  
        }  
    }  
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	//�ж�����һ����ĸ�������
    	int nIndex = (int) (event.getY() / getHeight() * ASSORT_TEXT.length);
    	if (nIndex >= 0 && nIndex < ASSORT_TEXT.length){
            switch (event.getAction()){  
            case MotionEvent.ACTION_MOVE:  
                // ��������ı�  
                if (mSelectIndex != nIndex){  
                	mSelectIndex = nIndex;
                	showCharacter(ASSORT_TEXT[mSelectIndex]);
                    if (mListener != null){
                    	mListener.onTouchAssortListener(ASSORT_TEXT[mSelectIndex]);
                    }
                }  
                break;  
            case MotionEvent.ACTION_DOWN:  
            	mSelectIndex = nIndex;  
            	showCharacter(ASSORT_TEXT[mSelectIndex]);
                if (mListener != null){  
                	mListener.onTouchAssortListener(ASSORT_TEXT[mSelectIndex]);  
                }  
  
                break;  
            case MotionEvent.ACTION_UP:  
            	disShowCharacter();
                mSelectIndex = -1;  
                break;  
            }  
        } else {  
        	mSelectIndex = -1;  
        	disShowCharacter();
        }  
    	invalidate();
    	return true;
    }

    private void disShowCharacter() {
    	if (mPopupWindow != null) {
    		mPopupWindow.dismiss();
    		mPopupWindow=null;
        }
	}

	/**
     * ��ʾ�������ַ�
     * @param string
     */
	private void showCharacter(String string){
		
		if (mPopupWindow != null){
            text.setText(string);
        } else{   
            mPopupWindow = new PopupWindow(layoutView, 150, 150, false);
            mPopupWindow.showAtLocation(mAttachActivity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
        text.setText(string);
	}
}  
