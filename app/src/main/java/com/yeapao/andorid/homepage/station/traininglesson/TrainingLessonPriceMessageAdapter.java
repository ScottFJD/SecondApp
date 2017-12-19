package com.yeapao.andorid.homepage.station.traininglesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeapao.andorid.R;
import com.yeapao.andorid.model.EmployeeDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fujindong on 2017/12/8.
 */

public class TrainingLessonPriceMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private static int pos = 111;
    private EmployeeDetailModel detailModel;
    private String type;

    public TrainingLessonPriceMessageAdapter(Context context, EmployeeDetailModel employeeDetailModel, String type) {
        this.type = type;
        detailModel = employeeDetailModel;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public interface ChooseStatusListener {
        void chooseStatus(boolean status,int position);
    }


    private ChooseStatusListener statusListener;

    public void setChooseStatusListener(ChooseStatusListener listener) {
        if (listener != null) {
            statusListener = listener;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_lesson_price, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((ViewHolder)holder).tvLessonName.setText(detailModel.getData().getEmployeeFeeOut().get(position).getFeeName());
        ((ViewHolder) holder).tvTrainingPrice.setText(mContext.getResources().getString(R.string.RMB)+String.valueOf(detailModel.getData().getEmployeeFeeOut().get(position).getPrice()));

        if (type.equals(TrainingLessonActivity.HIGH)) {
            ((ViewHolder) holder).tvLessonNum.setText("(共"+String.valueOf(detailModel.getData().getEmployeeFeeOut().get(position).getSubjectNum())+"课时)");

        } else {
            ((ViewHolder) holder).tvLessonNum.setText("(有效期"+String.valueOf(detailModel.getData().getEmployeeFeeOut().get(position).getDateRange())+"天)");

        }

        if (position != pos) {
            ((ViewHolder) holder).ivChooseStatus.setChecked(false);
        }

        ((ViewHolder) holder).ivChooseStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((ViewHolder) holder).ivChooseStatus.isChecked()) {
                    statusListener.chooseStatus(true,position);
                    pos = position;
                } else {
                    statusListener.chooseStatus(false,position);
                    pos = 111;
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return detailModel.getData().getEmployeeFeeOut().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lesson_name)
        TextView tvLessonName;
        @BindView(R.id.tv_lesson_num)
        TextView tvLessonNum;
        @BindView(R.id.iv_choose_status)
        CheckBox ivChooseStatus;
        @BindView(R.id.tv_training_price)
        TextView tvTrainingPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
