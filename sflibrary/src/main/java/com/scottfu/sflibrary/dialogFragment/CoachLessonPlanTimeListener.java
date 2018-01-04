package com.scottfu.sflibrary.dialogFragment;

/**
 * Created by fujindong on 2018/1/4.
 */

public interface CoachLessonPlanTimeListener {

    void timePickerCancel();

    void success(String time1, String time2, int day1);
}
