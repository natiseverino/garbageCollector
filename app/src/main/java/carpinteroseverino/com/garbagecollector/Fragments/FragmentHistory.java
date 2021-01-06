package carpinteroseverino.com.garbagecollector.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;

import carpinteroseverino.com.garbagecollector.API.ApiHandler;
import carpinteroseverino.com.garbagecollector.API.TotalResponseObj;
import carpinteroseverino.com.garbagecollector.R;
import carpinteroseverino.com.garbagecollector.SubmissionListActivity;

public class FragmentHistory extends Fragment {

    private static final String TAG = "FragmentHistory";

    private View view;
    private String username;

    private ArrayList<String> dataTitles = new ArrayList<String>(Arrays.asList("Botellas", "Papeles", "Vidrios", "Latas", "Tetrabriks"));
    private PieChart pieChart;
    private TextView textViewVol;
    private TotalResponseObj totalResponseObj;

    private FragmentsListener listener;


    public FragmentHistory() {
        Log.d(TAG, "CONSTRUCTOR");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        view = inflater.inflate(R.layout.fragment_history, container, false);
        textViewVol = view.findViewById(R.id.text_view_vol);

        pieChart = view.findViewById(R.id.piechart);
        pieChart.setRotationEnabled(false);
        pieChart.setHoleRadius(24f);
        pieChart.setTransparentCircleRadius(40f);
        pieChart.setUsePercentValues(true);
        pieChart.setTransparentCircleAlpha(128);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: highlight " + h.toString());
                Toast.makeText(getContext(), dataTitles.get((int) h.getX()) + ": " + (int) h.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        if (savedInstanceState != null)
            totalResponseObj = savedInstanceState.getParcelable("totalResponseObj");

        updateDataChart();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateDataChart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener = (FragmentsListener) getActivity();
        listener.setFragmentHistory(this);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.history_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickHistoryListButton();
            }
        });
    }

    public void updateDataChart() {
        if (totalResponseObj != null && view != null) {
            textViewVol.setText(totalResponseObj.getTons() + " m\u00B3");

            ArrayList<PieEntry> yEntry = new ArrayList<>();
            ArrayList<Integer> colors = new ArrayList<>();

            if (totalResponseObj.getBottles() > 0) {
                yEntry.add(new PieEntry(totalResponseObj.getBottles(), dataTitles.get(0)));
                colors.add(getResources().getColor(R.color.colorPink_300));
            }

            if (totalResponseObj.getPaperboard() > 0) {
                yEntry.add(new PieEntry(totalResponseObj.getPaperboard(), dataTitles.get(1)));
                colors.add(getResources().getColor(R.color.colorLightBlue_300));
            }

            if (totalResponseObj.getGlass() > 0) {
                yEntry.add(new PieEntry(totalResponseObj.getGlass(), dataTitles.get(2)));
                colors.add(getResources().getColor(R.color.colorGreen_400));
            }

            if (totalResponseObj.getCans() > 0) {
                yEntry.add(new PieEntry(totalResponseObj.getCans(), dataTitles.get(3)));
                colors.add(getResources().getColor(R.color.colorAmber_300));
            }

            if (totalResponseObj.getTetrabriks() > 0) {
                yEntry.add(new PieEntry(totalResponseObj.getTetrabriks(), dataTitles.get(4)));
                colors.add(getResources().getColor(R.color.colorDeepOrange_300));
            }


            PieDataSet pieDataSet = new PieDataSet(yEntry, "Reciclados");
            pieDataSet.setSliceSpace(2);
            pieDataSet.setValueTextSize(12);
            pieDataSet.setColors(colors);

            PieData pieData = new PieData(pieDataSet);
            pieData.setValueFormatter(new PercentFormatter());
            pieChart.setData(pieData);
            pieChart.notifyDataSetChanged();
            pieChart.invalidate();
        }

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalRecycled(TotalResponseObj totalResponseObj) {
        Log.d(TAG, "setTotalRecycled: totalRecycled: " + totalResponseObj);
        this.totalResponseObj = totalResponseObj;
        updateDataChart();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putParcelable("totalResponseObj", totalResponseObj);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewStateRestored: ");
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            totalResponseObj = savedInstanceState.getParcelable("totalResponseObj");
        }
    }

}
