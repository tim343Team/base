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
 * $底部弹出仿ios时间选择器
 *
 * eg:     PickTimeView timeView = new PickTimeView(RegisterCompleteActivity.this);
 *         timeView.setStartTime(1970, 1, 1, 0, 0, 0);
 *         timeView.setEndtTimeMillis();
 *         timeView.setTitle("生日");
 *         timeView.showTimePickerView();
 *         timeView.setOnTimeSelectListener(new PickTimeView.OnTimeSelect() {
 *             @Override
 *             public void onSelect(Date date) {
 *                 DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
 *                 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
 *                 tvBirthday.setText(df.format(date));
 *                 bean.setBirthday(df2.format(date));
 *             }
 *         });
 *
 * @author weiqiliu
 * @version 1.0 2020/4/26
 */
public class PickTimeView {
    Context context;
    String title;
    String cancelMessage="取消";
    String submitMessage="确认";
    String cancelColor="#ff7b7b7b";
    String submitColor="#ff7b7b7b";
    boolean isDialog=false;
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
            }
        }).setType(type)
                .setTitleText(title)
                .setCancelText(cancelMessage)//取消按钮文字
                .setSubmitText(submitMessage)//确认按钮文字
                .setSubCalSize(16)
                .setTitleColor(Color.parseColor("#ff000000"))
                .setSubmitColor(Color.parseColor(submitColor))//确定按钮文字颜色
                .setCancelColor(Color.parseColor(cancelColor))//取消按钮文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .isDialog(isDialog)
                .build();
        pvTime.show();
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public String getSubmitMessage() {
        return submitMessage;
    }

    public void setSubmitMessage(String submitMessage) {
        this.submitMessage = submitMessage;
    }

    public String getCancelColor() {
        return cancelColor;
    }

    public void setCancelColor(String cancelColor) {
        this.cancelColor = cancelColor;
    }

    public String getSubmitColor() {
        return submitColor;
    }

    public void setSubmitColor(String submitColor) {
        this.submitColor = submitColor;
    }

    public boolean isDialog() {
        return isDialog;
    }

    public void setDialog(boolean dialog) {
        isDialog = dialog;
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

    public void setEndtTimeMillis(long millis){
        endDate.setTimeInMillis(millis);
    }

    public void setStartTimeMillis(long millis){
        startDate.setTimeInMillis(millis);
    }


    OnTimeSelect timeSelect;

    public void setOnTimeSelectListener(OnTimeSelect timeSelect) {
        this.timeSelect = timeSelect;
    }

    public interface OnTimeSelect {

        void onSelect(Date date);
    }
}
