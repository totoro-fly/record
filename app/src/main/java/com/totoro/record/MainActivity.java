package com.totoro.record;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.totoro.record.data.RecordContract;
import com.totoro.record.data.RecordDbHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    RecordDbHelper recordDbHelper;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.record_textview)
    TextView recordTextview;
    @Bind(R.id.add_record_button)
    FloatingActionButton addRecordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("习惯记录器");
        setSupportActionBar(toolbar);
        recordDbHelper = new RecordDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_updata:
                break;
            case R.id.action_delete:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_record_button)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddRecordActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        recordTextview.setText("");
        displayDatabaseInfo();
        super.onStart();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = recordDbHelper.getReadableDatabase();
        Cursor cursor = recordDbHelper.readAllData(db);
        try {
            int yearColumnIndex = cursor.getColumnIndex(RecordContract.RecordEntry.COLUMN_RECORD_YEAR);
            int monthColumnIndex = cursor.getColumnIndex(RecordContract.RecordEntry.COLUMN_RECORD_MONTH);
            int dayColumnIndex = cursor.getColumnIndex(RecordContract.RecordEntry.COLUMN_RECORD_DAY);
            int messageColumnIndex = cursor.getColumnIndex(RecordContract.RecordEntry.COLUMN_RECORD_MESSAGE);
            while (cursor.moveToNext()) {
                String currentYear = cursor.getString(yearColumnIndex);
                String currentMonth = cursor.getString(monthColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                String currentdate = currentYear + "/" + currentMonth + "/" + currentDay;
                String currentMessage = cursor.getString(messageColumnIndex);
                recordTextview.append(currentdate + "\n\n" + currentMessage + "\n\n");
            }
        } finally {
            cursor.close();
            db.close();
        }
    }
}
