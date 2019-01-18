package soexample.umeng.com.dianshangproject.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * author:author${朱佳华}
 * data:2019/1/9
 */
public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "dianshang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table news (text name)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
