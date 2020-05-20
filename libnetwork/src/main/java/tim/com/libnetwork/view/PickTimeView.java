package tim.com.libnetwork.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Calendar;
import java.util.Date;

import tim.com.libnetwork.R;

/**
 * ${description}
 *
 * @author weiqiliu
 * @version 1.0 2020/4/26
 */
public class PickTimeView {
    Context context;
    String title;
    boolean[] type=new boolean[]{true, true, true, false, false, false}; //分别控制“年”“月”“日”“时”“分”“秒”的显示或隐藏。
    Calendar startDate = Calendar.getInstance();
    Calendar endDate = Calendar.getInstance();

    public PickTimeView(Context context) {
        this.context = context;
    }

    public void showTimePickerView() {
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                timeSelect.onSelect(date);
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00");
//                tvTime.setText(df.format(date));
//                if (isStart) {
//                    startTime = WonderfulStringUtils.dateToLong(date) + "";
//                } else {
//                    endTime = WonderfulStringUtils.dateToLong(date) + "";
//                }
            }
        }).setType(type)
                .setTitleText(title)
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setSubCalSize(16)
                .setTitleColor(Color.parseColor("#ff000000"))
                .setSubmitColor(Color.parseColor("#ff333333"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#ff333333"))//取消按钮文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .build();
        pvTime.show();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(boolean[] type) {
        this.type = type;
    }

    public void setStartTime(int year, int month, int date, int hourOfDay, int minute,
                             int second){
        startDate.set(year, month, date, date, minute, second);
    }

    public void setEndtTime(int year, int month, int date, int hourOfDay, int minute,
                             int second){
        endDate.set(year, month, date, date, minute, second);
    }

    public void setEndtTimeMillis(){
        endDate.setTimeInMillis(System.currentTimeMillis());
    }

    public void setStartTimeMillis(){
        startDate.setTimeInMillis(System.currentTimeMillis());
    }

    OnTimeSelect timeSelect;

    public void setOnTimeSelectListener(OnTimeSelect timeSelect) {
        this.timeSelect = timeSelect;
    }

    public interface OnTimeSelect {

        void onSelect(Date date);
    }
}
