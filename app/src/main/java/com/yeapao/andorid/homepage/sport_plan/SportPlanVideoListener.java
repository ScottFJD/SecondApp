package com.yeapao.andorid.homepage.sport_plan;

/**
 * Created by fujindong on 2017/10/26.
 */

public interface SportPlanVideoListener {
    void sportPlanVideoClickListener(String videoId, String programmeId);

    void refreshSportPlanStatus(int dayId, int videoType);
}
