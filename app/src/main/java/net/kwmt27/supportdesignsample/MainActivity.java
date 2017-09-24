package net.kwmt27.supportdesignsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ActivityNameClass> activityNames = new ArrayList<>();
        activityNames.add(new ActivityNameClass(BottomNavigationActivity.class));
        activityNames.add(new ActivityNameClass(BottomSheetModalActivity.class));


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter  = new RecyclerViewAdapter(this, activityNames, new RecyclerViewAdapter.OnRecyclerListener() {
            @Override
            public void onRecyclerClicked(View v, ActivityNameClass activityName, int position) {
                Intent intent = new Intent(MainActivity.this, activityName.aClass);
                startActivity(intent);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private static class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final LayoutInflater inflater;

        private final OnRecyclerListener onClickListener;
        private List<ActivityNameClass> activityNames = new ArrayList<>();

        public interface OnRecyclerListener {

            void onRecyclerClicked(View v, ActivityNameClass activityNameClass, int position);

        }

        public RecyclerViewAdapter(Context context, List<ActivityNameClass> activityNames, OnRecyclerListener listener) {
            inflater = LayoutInflater.from(context);

            this.activityNames = activityNames;
            this.onClickListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.recyclerview_activiyty_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.textView.setText(activityNames.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null) {
                        onClickListener.onRecyclerClicked(view, activityNames.get(position), position);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return activityNames.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.activity_name);

            }
        }
    }

    private static class ActivityNameClass {

        Class aClass;

        public ActivityNameClass(Class aClass) {
            this.aClass = aClass;
        }

        public String getName() {
            return aClass.getSimpleName();
        }
    }
}
