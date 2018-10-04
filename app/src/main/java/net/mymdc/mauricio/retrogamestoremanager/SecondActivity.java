package net.mymdc.mauricio.retrogamestoremanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    public static final String CONSOLE_ID = "Console ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void viewTest(View view) {

        int message;
        String title = getString(R.string.current_game_title);

        switch(view.getId()) {
            case R.id.imageButton: message = R.integer.ATARI_2600; break;
            case R.id.imageButton2: message = R.integer.INTELLIVISION; break;
            case R.id.imageButton3: message = R.integer.COLECOVISION; break;
            case R.id.imageButton4: message = R.integer.NES; break;
            default: message = -1; break;
        }

        Intent toNext = new Intent(this, ThirdActivity.class);
        toNext.putExtra(title, getIntent().getStringExtra(title));
        toNext.putExtra(getString(R.string.current_console), message);
        startActivity(toNext);
    }
}
