package soexample.umeng.com.dianshangproject.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * author:author${朱佳华}
 * data:2019/1/9
 */
public class Dao {
    private SQLiteDatabase database;

    public Dao(Context context) {
        MyHelper helper = new MyHelper(context);
        database = helper.getWritableDatabase();
    }

    public void add(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        database.insert("news", null, contentValues);
    }

    public List<String> cha() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.query("news", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

    public void del() {
        database.execSQL("delete from news");  //全部删除  删除整个数据库
    }
}
