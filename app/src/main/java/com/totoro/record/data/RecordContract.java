package com.totoro.record.data;

import android.provider.BaseColumns;

/**
 * Created by totoro-fly on 2017/4/28.
 */

public class RecordContract {
    public RecordContract() {
    }

    public static final class RecordEntry implements BaseColumns {
        public static final String TABLE_NAME = "record";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_RECORD_YEAR = "year";
        public static final String COLUMN_RECORD_MONTH = "month";
        public static final String COLUMN_RECORD_DAY = "day";
        public static final String COLUMN_RECORD_MESSAGE = "message";

    }
}
