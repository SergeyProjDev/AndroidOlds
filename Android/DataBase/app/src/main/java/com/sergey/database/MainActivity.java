package com.sergey.database;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Serializable{

    ArrayList<Searcher> arrayList = new ArrayList<>();
    EditText sear;
    Button btn;
    EditText login, password, site;
    DBHelper dbHelper = new DBHelper(this);
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        site = findViewById(R.id.what);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        sear = findViewById(R.id.searching);
        table = findViewById(R.id.table_layout);

        sear.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,  int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String weSearch = sear.getText().toString();

                if (weSearch.equals("")) {
                    showTableDataBase(null);
                }
                else
                {
                    table.removeAllViews();
                    setColumnNames(table);

                    boolean colorSet = false;
                    for (int i = 0; i < arrayList.size(); i++){
                        Searcher ser = new Searcher();
                        ser = arrayList.get(i);
                        String idStr = String.valueOf(ser.getID());
                        String siteStr = ser.getSite();
                        String logStr = ser.getLogin();
                        String passStr = ser.getPassword();

                        if ((idStr.contains(weSearch)) || (siteStr.contains(weSearch)) || (logStr.contains(weSearch))) {
                                addThisTableRow(idStr, logStr, siteStr, passStr, colorSet);
                                colorSet = !colorSet;
                        }
                    }
                }
            }
        });

        showTableDataBase(null);
    }

    public void addToDataBase(View v){
        Encryptor encryptor = new Encryptor();
        String loginStr = encryptor.encryptWord(login.getText().toString());
        String passwordStr = encryptor.encryptWord(password.getText().toString());
        String siteNameStr = encryptor.encryptWord(site.getText().toString());

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_SITENAME, siteNameStr);
        contentValues.put(DBHelper.KEY_LOGIN, loginStr);
        contentValues.put(DBHelper.KEY_PASSWORD, passwordStr);

        database.insert(DBHelper.TABLE_INFORMATION, null, contentValues);

        dbHelper.close();
        showTableDataBase(null);
    }

    public void showTableDataBase(View v){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_INFORMATION, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int siteNameIndex = cursor.getColumnIndex(DBHelper.KEY_SITENAME);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);

            table.removeAllViews();

            setColumnNames(table);

            arrayList.clear();
            boolean flag = false;
            do {
                Encryptor encryptor = new Encryptor();
                Searcher searcher = new Searcher();
                searcher.setID(Integer.parseInt(String.valueOf(cursor.getInt(idIndex))));
                searcher.setSite(encryptor.decryptWord(String.valueOf(cursor.getString(siteNameIndex))));
                searcher.setLogin(encryptor.decryptWord(String.valueOf(cursor.getString(nameIndex))));
                searcher.setPassword(encryptor.decryptWord(String.valueOf(cursor.getString(emailIndex))));
                arrayList.add(searcher);

                addThisTableRow(String.valueOf(searcher.getID()), searcher.getLogin(), searcher.getSite(), searcher.getPassword(), flag);

                flag = !flag;
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }

    public void delById(View v){
        EditText idToDel = findViewById(R.id.editText6);

        try{
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            database.delete(DBHelper.TABLE_INFORMATION, DBHelper.KEY_ID + "=" + idToDel.getText().toString(), null);
            dbHelper.close();
            idToDel.setText("");
            showTableDataBase(null);
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void showPopur(View v) {   //меню
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu0:
                        //изменить пароль
                        try{
                            File file = new File(StartActivity.fileName);
                            file.delete();
                            file.getCanonicalFile().delete();
                            getApplicationContext().deleteFile(file.getName());
                            Toast.makeText(getApplicationContext(), "Password deleted. Pick new, after restarting application", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.menu1:
                        //импорт
                        importPass();
                        break;
                    case R.id.menu2:
                        //экспорт
                        exportPass();
                        break;
                }


                return true;
            }
        });
        popup.inflate(R.menu.menu);
        popup.show();
    }




    private void setColumnNames(TableLayout table){
        TextView textID = new TextView(getApplicationContext());
        textID.setPadding(5, 0,5, 2);
        textID.setWidth(50);
        textID.setHeight(50);
        textID.setTextSize(14);
        textID.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textID.setBackgroundColor(Color.rgb(0, 102, 0));
        textID.setTextColor(Color.WHITE);
        textID.setGravity(Gravity.CENTER_HORIZONTAL);
        textID.setGravity(Gravity.CENTER_VERTICAL);
        textID.setText("ID");

        TextView textLogin = new TextView(getApplicationContext());
        textLogin.setPadding(5, 0,5, 2);
        textLogin.setWidth(400);
        textLogin.setHeight(50);
        textLogin.setTextSize(14);
        textLogin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textLogin.setBackgroundColor(Color.rgb(0, 102, 0));
        textLogin.setTextColor(Color.WHITE);
        textLogin.setGravity(Gravity.CENTER_HORIZONTAL);
        textLogin.setGravity(Gravity.CENTER_VERTICAL);
        textLogin.setText("Login");

        TextView siteName = new TextView(getApplicationContext());
        siteName.setPadding(5, 0,5, 2);
        siteName.setWidth(250);
        siteName.setHeight(50);
        siteName.setTextSize(14);
        siteName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        siteName.setBackgroundColor(Color.rgb(0, 102, 0));
        siteName.setTextColor(Color.WHITE);
        siteName.setGravity(Gravity.CENTER_HORIZONTAL);
        siteName.setGravity(Gravity.CENTER_VERTICAL);
        siteName.setText("Site");

        TextView textPassword = new TextView(getApplicationContext());
        textPassword.setPadding(5, 0,5, 2);
        textPassword.setWidth(250);
        textPassword.setHeight(50);
        textPassword.setTextSize(14);
        textPassword.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textPassword.setBackgroundColor(Color.rgb(0, 102, 0));
        textPassword.setTextColor(Color.WHITE);
        textPassword.setGravity(Gravity.CENTER_HORIZONTAL);
        textPassword.setGravity(Gravity.CENTER_VERTICAL);
        textPassword.setText("Password");

        TableRow row = new TableRow(getApplicationContext());
        row.addView(textID);
        row.addView(siteName);
        row.addView(textLogin);
        row.addView(textPassword);


        table.addView(row);
    }

    int setedId = 1234;
    private void addThisTableRow(String id, String site, String login, String password, Boolean flag){
        TextView textID = new TextView(getApplicationContext());
        textID.isClickable();
        textID.setId(setedId); setedId ++;
        textID.setWidth(50);
        textID.setHeight(120);
        textID.setTextSize(14);
        textID.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if (flag) textID.setBackgroundColor(0xffcccccc);
        else textID.setBackgroundColor(0xffffffff);
        textID.setGravity(Gravity.CENTER_HORIZONTAL);
        textID.setGravity(Gravity.CENTER_VERTICAL);
        textID.setTextColor(0xff000000);
        textID.setText(id);


        EditText textLogin = new EditText(getApplicationContext());
        textLogin.setTextIsSelectable(false);
        textLogin.setFocusable(false);
        textLogin.isClickable();
        textLogin.setId(setedId); setedId ++;
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                TextView tv = findViewById(v.getId());
                String text = tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(), "Copied: "+text, Toast.LENGTH_SHORT).show();
            }
        });
        textLogin.setWidth(400);
        textLogin.setHeight(120);
        textLogin.setTextSize(14);
        textLogin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if (flag) textLogin.setBackgroundColor(0xffcccccc);
        textLogin.setGravity(Gravity.CENTER_HORIZONTAL);
        textLogin.setGravity(Gravity.CENTER_VERTICAL);
        textLogin.setTextColor(0xff000000);
        textLogin.setText(login);


        EditText siteName = new EditText(getApplicationContext());
        siteName.setFocusable(false);
        siteName.setTextIsSelectable(false);
        siteName.isClickable();
        siteName.setId(setedId); setedId ++;
        siteName.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                TextView tv = findViewById(v.getId());
                String text = tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(), "Copied: "+text, Toast.LENGTH_SHORT).show();
            }
        });
        siteName.setWidth(250);
        siteName.setHeight(120);
        siteName.setTextSize(14);
        siteName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if (flag) siteName.setBackgroundColor(0xffcccccc);
        siteName.setGravity(Gravity.CENTER_HORIZONTAL);
        siteName.setGravity(Gravity.CENTER_VERTICAL);
        siteName.setTextColor(0xff000000);
        siteName.setText(site);

        TextView textPassword = new TextView(getApplicationContext());
        textPassword.isClickable();
        textPassword.setId(setedId); setedId ++;
        textPassword.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                TextView tv = findViewById(v.getId());
                String text = tv.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getBaseContext(), "Copied: "+text, Toast.LENGTH_SHORT).show();
            }
        });
        textPassword.setWidth(250);
        textPassword.setHeight(120);
        textPassword.setTextSize(14);
        textPassword.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if (flag) textPassword.setBackgroundColor(0xffcccccc);
        textPassword.setGravity(Gravity.CENTER_HORIZONTAL);
        textPassword.setGravity(Gravity.CENTER_VERTICAL);
        textPassword.setTextColor(0xff000000);
        textPassword.setText(password);



        TableRow row = new TableRow(getApplicationContext());
        row.addView(textID);
        row.addView(siteName);
        row.addView(textLogin);
        row.addView(textPassword);


        table.addView(row);
    }

    private void importPass(){
            try {
                File sd = Environment.getExternalStorageDirectory();
                File data  = Environment.getDataDirectory();

                if (sd.canWrite()) {
                    String  currentDBPath= "//data//" + getPackageName() + "//databases//" + DBHelper.DATABASE_NAME;
                    String backupDBPath  = "/storage/emulated/0/database.db";
                    File  backupDB= new File(data, currentDBPath);
                    File currentDB  = new File(backupDBPath);

                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(getBaseContext(), "Imported!", Toast.LENGTH_LONG).show();
                    showTableDataBase(null);
                }
                else {
                    Toast.makeText(getBaseContext(), "I can`t!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Put file \"database.db\" to \n/storage/emulated/0/database.db", Toast.LENGTH_LONG).show();
            }
    }
    private void exportPass(){
          try {
                File sd = Environment.getExternalStorageDirectory();
                File data = Environment.getDataDirectory();
                if (sd.canWrite()) {
                    String  currentDBPath= "//data//" + getPackageName() + "//databases//" + DBHelper.DATABASE_NAME;
                    String backupDBPath  = "/storage/emulated/0/database.db";
                    File currentDB = new File(data, currentDBPath);
                    File backupDB = new File(backupDBPath);

                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(getBaseContext(), "Exported to: " + backupDB.toString(), Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "I can`t!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                        .show();

        }
    }
}



class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_INFORMATION = "information";

    public static final String KEY_ID = "_id";
    public static final String KEY_SITENAME = "name";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_INFORMATION + "(" + KEY_ID + " integer primary key," + KEY_SITENAME + " text," + KEY_LOGIN + " text," + KEY_PASSWORD + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_INFORMATION);

        onCreate(db);
    }
}



class Searcher {
    private String site;
    private String login;
    private String password;
    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }
}

