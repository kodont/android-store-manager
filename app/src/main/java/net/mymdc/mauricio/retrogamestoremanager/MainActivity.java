package net.mymdc.mauricio.retrogamestoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toGameDetails(View view) {

        EditText titleField = findViewById(R.id.titleField);
        String title = titleField.getText().toString();
        if(title.equals("")) {
            Toast.makeText(this, "Invalid title", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(getString(R.string.current_game_title), title);
        startActivity(intent);
    }

    public void toLastGame(View view) {

        SharedPreferences prefs = getSharedPreferences(
                getString(R.string.preferences_name), MODE_PRIVATE);
        if(prefs.getString(getString(R.string.last_game_title),
                "null").equals("null")) {
            Toast.makeText(this, "No sale data saved",
                    Toast.LENGTH_LONG).show();
            return;
        }

        startActivity(new Intent(this, FourthActivity.class));

    }

    public void clearData(View view) {

        SharedPreferences.Editor editPrefs = getSharedPreferences(
                getString(R.string.preferences_name), MODE_PRIVATE).edit();
        editPrefs.remove(getString(R.string.last_game_title));
        editPrefs.apply();
        Toast.makeText(this, "Data cleared",
                Toast.LENGTH_LONG).show();
    }

}
