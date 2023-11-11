package com.ouslproject.medicalreminderapp.activity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ouslproject.medicalreminderapp.R;
import com.ouslproject.medicalreminderapp.adapter.TaskAdapter;
import com.ouslproject.medicalreminderapp.bottomSheetFragment.CreateTaskBottomSheetFragment;
import com.ouslproject.medicalreminderapp.bottomSheetFragment.ShowCalendarViewBottomSheet;
import com.ouslproject.medicalreminderapp.broadcastReceiver.AlarmBroadcastReceiver;
import com.ouslproject.medicalreminderapp.database.DatabaseClient;
import com.ouslproject.medicalreminderapp.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//displays the saved tasks in a RecyclerView and provides options to add new tasks and view the calendar
public class MainActivity3 extends BaseActivity implements CreateTaskBottomSheetFragment.setRefreshListener {

    @BindView(R.id.taskRecycler)
    RecyclerView taskRecycler;
    @BindView(R.id.addTask)
    TextView addTask;
    TaskAdapter taskAdapter;
    List<Task> tasks = new ArrayList<>();
    @BindView(R.id.noDataImage)
    ImageView noDataImage;
    @BindView(R.id.calendar)
    ImageView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        setUpAdapter();
//sets a flag to keep the screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//enables the AlarmBroadcastReceiver using the PackageManager
        ComponentName receiver = new ComponentName(this, AlarmBroadcastReceiver.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Glide.with(getApplicationContext()).load(R.drawable.mainpic1).into(noDataImage);//initializes the noDataImage and sets an image using Glide
//add a new task
        addTask.setOnClickListener(view -> {
            CreateTaskBottomSheetFragment createTaskBottomSheetFragment = new CreateTaskBottomSheetFragment();
            createTaskBottomSheetFragment.setTaskId(0, false, this, MainActivity3.this);
            createTaskBottomSheetFragment.show(getSupportFragmentManager(), createTaskBottomSheetFragment.getTag());
        });
// tasks are retrieved from the database
        getSavedTasks();
//opens a ShowCalendarViewBottomSheet fragment to display the calendar view
        calendar.setOnClickListener(view -> {
            ShowCalendarViewBottomSheet showCalendarViewBottomSheet = new ShowCalendarViewBottomSheet();
            showCalendarViewBottomSheet.show(getSupportFragmentManager(), showCalendarViewBottomSheet.getTag());
        });
    }

    public void setUpAdapter() {
        taskAdapter = new TaskAdapter(this, tasks, this);
        taskRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        taskRecycler.setAdapter(taskAdapter);
    }
//retrieves the saved tasks from the database and updates the UI
    private void getSavedTasks() {

        class GetSavedTasks extends AsyncTask<Void, Void, List<Task>> {
            @Override
            protected List<Task> doInBackground(Void... voids) {
                tasks = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .dataBaseAction()
                        .getAllTasksList();
                return tasks;
            }
//update main thread
            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                noDataImage.setVisibility(tasks.isEmpty() ? View.VISIBLE : View.GONE);
                setUpAdapter();
            }
        }

        GetSavedTasks savedTasks = new GetSavedTasks();
        savedTasks.execute();
    }
//refreshing when a new task is added
    @Override
    public void refresh() {
        getSavedTasks();
    }
}
