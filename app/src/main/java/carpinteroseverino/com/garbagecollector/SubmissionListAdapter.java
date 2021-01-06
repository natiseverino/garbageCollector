package carpinteroseverino.com.garbagecollector;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;

public class SubmissionListAdapter extends RecyclerView.Adapter<SubmissionListAdapter.ViewHolder> {

    ArrayList<RecyclingResponseObj> dataset;


    public SubmissionListAdapter() {
        this.dataset = new ArrayList<RecyclingResponseObj>();
    }

    public ArrayList<RecyclingResponseObj> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<RecyclingResponseObj> dataset) {
        this.dataset = dataset;
        Collections.sort(dataset, Collections.<RecyclingResponseObj>reverseOrder());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_submission,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclingResponseObj actualRecycled=dataset.get(position);
        holder.textViewBotellas.setText(Integer.toString(actualRecycled.getBottles()));
        holder.textViewLatas.setText(Integer.toString(actualRecycled.getCans()));
        holder.textViewPapeles.setText(Integer.toString(actualRecycled.getPaperboard()));
        holder.textViewVidrio.setText(Integer.toString(actualRecycled.getGlass()));
        holder.textViewTetrabriks.setText(Integer.toString(actualRecycled.getTetrabriks()));
        holder.textViewFecha.setText(actualRecycled.getDate());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewBotellas;
        TextView textViewPapeles;
        TextView textViewLatas;
        TextView textViewVidrio;
        TextView textViewTetrabriks;
        TextView textViewFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewBotellas=itemView.findViewById(R.id.row_submission_bottles);
            textViewPapeles=itemView.findViewById(R.id.row_submission_paperboard);
            textViewLatas=itemView.findViewById(R.id.row_submission_cans);
            textViewVidrio=itemView.findViewById(R.id.row_submission_glass);
            textViewTetrabriks=itemView.findViewById(R.id.row_submission_tetrabriks);
            textViewFecha=itemView.findViewById(R.id.row_submission_date);
        }
    }
}
