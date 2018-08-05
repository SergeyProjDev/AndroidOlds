package com.sergey.notepad;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ClipboardManager clipboard;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(bar);

        myEditText = (EditText) findViewById(R.id.editText);
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    public void onCopy(View v) {
        ClipData myClip;
        int min = 0;
        int max = myEditText.getText().length();
        if (myEditText.isFocused()) {
            final int selStart = myEditText.getSelectionStart();
            final int selEnd = myEditText.getSelectionEnd();
            min = Math.max(0, Math.min(selStart, selEnd));
            max = Math.max(0, Math.max(selStart, selEnd));
        }
        final CharSequence selectedText = myEditText.getText().subSequence(min, max);
        String text = selectedText.toString();

        myClip = ClipData.newPlainText(getString(R.string.text), text);
        clipboard.setPrimaryClip(myClip);
        Toast.makeText(this, R.string.wasCopied, Toast.LENGTH_SHORT).show();
    }

    public void onDel(View v) {
        myEditText.setText("");
    }

    public void onPaste(View v) {
        String textToPaste = clipboard.getText().toString();
        int start = Math.max(myEditText.getSelectionStart(), 0);
        int end = Math.max(myEditText.getSelectionEnd(), 0);
        myEditText.getText().replace(Math.min(start, end), Math.max(start, end), textToPaste, 0, textToPaste.length());
        if (textToPaste.equals("")) {
            Toast.makeText(this, R.string.pasteNothing, Toast.LENGTH_SHORT).show();
        }
    }

    public void saveText(View v) {
        FileOutputStream fos = null;
        try {
            String text = myEditText.getText().toString();

            fos = openFileOutput(getString(R.string.toSave), MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, R.string.fileSaved, Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(View v) {
        FileInputStream fin = null;
        try {
            fin = openFileInput(getString(R.string.toSave));
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            myEditText.setText(text);
            Toast.makeText(this, R.string.fileOpened, Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}



