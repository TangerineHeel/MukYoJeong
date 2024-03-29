package com.jidokhants.mukyojeong.fragments.calendar.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jidokhants.mukyojeong.fragments.calendar.FragmentInsertDiet;
import com.jidokhants.mukyojeong.activities.MainActivity;
import com.jidokhants.mukyojeong.data.MukDBHelper;
import com.jidokhants.mukyojeong.R;
import com.jidokhants.mukyojeong.model.Record;
import com.jidokhants.mukyojeong.views.adapters.CalendarTextAdapter;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentCalendarCal extends Fragment {
    TextView selectedDateTextView;
    static String selectedDate, selectedDateParsed;

    ArrayList<Record> breakfastList, lunchList, dinnerList, snackList;

    MukDBHelper mukDBHelper;

    public static FragmentCalendarCal newInstance() {
        FragmentCalendarCal fragment = new FragmentCalendarCal();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).updateFoodsInLocal();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_cal, container, false);

        selectedDateTextView = view.findViewById(R.id.cal_today_date);

        Date currentTime = Calendar.getInstance().getTime();
        selectedDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(currentTime);
        selectedDateTextView.setText(selectedDate);

        mukDBHelper = MukDBHelper.getInstance(getContext());

        selectedDateParsed = selectedDate.replaceAll("/", "");

        breakfastList = mukDBHelper.getRecord(selectedDateParsed, 1);
        lunchList = mukDBHelper.getRecord(selectedDateParsed, 2);
        dinnerList = mukDBHelper.getRecord(selectedDateParsed,3);
        snackList = mukDBHelper.getRecord(selectedDateParsed, 4);
        Log.d("record selecting", "selectedDate: " + selectedDateParsed);
//        Log.d("breakFast", "size: "+breakfastList.size());
//        for (int i = 0; i < breakfastList.size(); i++) {
//            Log.d("breakFast", breakfastList.get(i).getFood().getName());
//        }

        RecyclerView rv_breakfast = view.findViewById(R.id.rv_cal_breakfast);
        RecyclerView rv_lunch = view.findViewById(R.id.rv_cal_lunch);
        RecyclerView rv_dinner = view.findViewById(R.id.rv_cal_dinner);
        RecyclerView rv_snack = view.findViewById(R.id.rv_cal_snack);

        rv_breakfast.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_lunch.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_dinner.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_snack.setLayoutManager(new LinearLayoutManager(getContext()));

        final CalendarTextAdapter adapter1 = new CalendarTextAdapter(breakfastList);
        final CalendarTextAdapter adapter2 = new CalendarTextAdapter(lunchList);
        final CalendarTextAdapter adapter3 = new CalendarTextAdapter(dinnerList);
        final CalendarTextAdapter adapter4 = new CalendarTextAdapter(snackList);

        rv_breakfast.setAdapter(adapter1);
        rv_lunch.setAdapter(adapter2);
        rv_dinner.setAdapter(adapter3);
        rv_snack.setAdapter(adapter4);

        ImageButton btn_add = view.findViewById(R.id.btn_add_cal);

        final CollapsibleCalendar collapsibleCalendar = view.findViewById(R.id.collapsibleCalendar2);
        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {


            @Override
            public void onDaySelect() {
                Day day = collapsibleCalendar.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
                selectedDate = day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay();
                selectedDateTextView.setText(selectedDate);
                selectedDateParsed = selectedDate.replaceAll("/", "");

                breakfastList = mukDBHelper.getRecord(selectedDateParsed, 1);
                lunchList = mukDBHelper.getRecord(selectedDateParsed, 2);
                dinnerList = mukDBHelper.getRecord(selectedDateParsed,3);
                snackList = mukDBHelper.getRecord(selectedDateParsed, 4);
                adapter1.updateItemList(breakfastList);
                adapter2.updateItemList(lunchList);
                adapter3.updateItemList(dinnerList);
                adapter4.updateItemList(snackList);
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }

            public void onDayChanged() {

            }


            public void onClickListener() {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceCalendarFragment(FragmentInsertDiet.newInstance(selectedDate));
            }
        });

        return view;
    }
}
