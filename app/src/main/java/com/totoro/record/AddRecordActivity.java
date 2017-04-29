package com.totoro.record;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import com.totoro.record.data.RecordContract;
import com.totoro.record.data.RecordDbHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddRecordActivity extends AppCompatActivity {
    RecordDbHelper recordDbHelper;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.datePicker)
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ButterKnife.bind(this);
        setTitle("添加一条记录");
        recordDbHelper = new RecordDbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insert();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insert() {
        SQLiteDatabase db = recordDbHelper.getReadableDatabase();
        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1;
        int day = datePicker.getDayOfMonth();
        String messageRecord = editText.getText().toString().trim();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RecordContract.RecordEntry.COLUMN_RECORD_YEAR, year);
        contentValues.put(RecordContract.RecordEntry.COLUMN_RECORD_MONTH, month);
        contentValues.put(RecordContract.RecordEntry.COLUMN_RECORD_DAY, day);
        contentValues.put(RecordContract.RecordEntry.COLUMN_RECORD_MESSAGE, messageRecord);
        db.insert(RecordContract.RecordEntry.TABLE_NAME, null, contentValues);
        db.close();

    }
}
