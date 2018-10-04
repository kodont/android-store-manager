package net.mymdc.mauricio.retrogamestoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        SharedPreferences pref = getSharedPreferences(
                getString(R.string.preferences_name), MODE_PRIVATE);

        TextView resultsText1 = findViewById(R.id.resultsText1);
        TextView resultsText2 = findViewById(R.id.resultsText2);
        TextView resultsText3 = findViewById(R.id.resultsText3);
        TextView resultsText4 = findViewById(R.id.resultsText4);
        TextView resultsText5 = findViewById(R.id.resultsText5);
        TextView resultsText6 = findViewById(R.id.resultsText6);
        String gameTitle = pref.getString(this.getString(
                R.string.last_game_title), "");
        String dateSold = pref.getString(this.getString(
                R.string.last_time), "");
        String price = pref.getString(this.getString(
                R.string.last_price), "");
        String console;
        switch (pref.getInt(getString(R.string.last_console), -1)) {
            case R.integer.ATARI_2600: console = "Atari 2600"; break;
            case R.integer.INTELLIVISION: console = "IntelliVision"; break;
            case R.integer.COLECOVISION: console = "ColecoVision"; break;
            case R.integer.NES: console = "NES"; break;
            default: console = "invalid";
        }
        String condition;
        switch (pref.getInt(this.getString(R.string.last_condition), -1)) {
            case 1: condition = "Like New"; break;
            case 2: condition = "Very Good"; break;
            case 3: condition = "Good"; break;
            case 4: condition = "Fair"; break;
            default: condition = "invalid";
        }

        boolean hasDisc = pref.getBoolean(this.getString(
                R.string.last_has_disc), false);
        boolean hasBox = pref.getBoolean(
                this.getString(R.string.last_has_box), false);
        boolean hasManual = pref.getBoolean(
                this.getString(R.string.last_has_manual), false);



        resultsText1.setText("You sold " + gameTitle + " for the " + console);
        resultsText2.setText("on " + dateSold);
        resultsText3.setText("This game was sold for");
        resultsText4.setText("$" + price);
        resultsText5.setText("The game was in " + condition + " condition.");

        StringBuilder sb = new StringBuilder("This sale included ");
        if(hasDisc) {
            sb.append("the game");
            if (hasBox && hasManual)
                sb.append(", ");
            else if (hasBox ^ hasManual)
                sb.append(", and ");
            else
                sb.append(".");
        }
        if(hasBox) {
            sb.append("the box");
            if(hasManual)
                sb.append(", and ");
            else
                sb.append(".");
        }
        if(hasManual) {
            sb.append("the manual.");
        }
        resultsText6.setText(sb.toString());

    }

    public void toHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
