package com.example.admin.w4d3logcalls;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivityTAG_";

    private static final String SMS_CONTENT_URI = "content://call_log/calls";

    private static final int LIST_ID = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(LIST_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                Uri.parse(SMS_CONTENT_URI),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                final String Number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                final String Name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                Log.d(TAG, "onLoadFinished: " + Number + " " + Name);
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
