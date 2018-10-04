package net.mymdc.mauricio.retrogamestoremanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void allChecked(View view) {

        CheckBox fullSetCheckBox = findViewById(R.id.fullSetCheckBox);
        CheckBox discCheckBox = findViewById(R.id.discCheckBox);
        CheckBox boxCheckBox = findViewById(R.id.boxCheckBox);
        CheckBox manualCheckBox = findViewById(R.id.manualCheckBox);

        if (view.getId() == R.id.fullSetCheckBox) {
            if (fullSetCheckBox.isChecked()) {
                discCheckBox.setChecked(true);
                boxCheckBox.setChecked(true);
                manualCheckBox.setChecked(true);
            }
            else {
                manualCheckBox.setChecked(false);
            }
        }
        else {
            fullSetCheckBox.setChecked(discCheckBox.isChecked() &&
                    boxCheckBox.isChecked() &&
                    manualCheckBox.isChecked());
        }
    }

    public void toSummary(View view) {

        RadioGroup conditionGroup = findViewById(R.id.radioGroup);
        RadioButton selectedCondition = findViewById(conditionGroup.
                getCheckedRadioButtonId());
        int conditionInt;

        switch(conditionGroup.getCheckedRadioButtonId()) {
            case R.id.radioButton: conditionInt = getResources().
                    getInteger(R.integer.CONDITION_LIKE_NEW); break;
            case R.id.radioButton2: conditionInt = getResources().
                    getInteger(R.integer.CONDITION_VERY_GOOD); break;
            case R.id.radioButton3: conditionInt = getResources().
                    getInteger(R.integer.CONDITION_GOOD); break;
            case R.id.radioButton4: conditionInt = getResources().
                    getInteger(R.integer.CONDITION_FAIR); break;
            default: conditionInt = -1; Toast.makeText(this,
                    "Please enter a condition", Toast.LENGTH_LONG).show();
                        return;
        }

        CheckBox fullSetCheckBox = findViewById(R.id.fullSetCheckBox);
        CheckBox discCheckBox = findViewById(R.id.discCheckBox);
        CheckBox boxCheckBox = findViewById(R.id.boxCheckBox);
        CheckBox manualCheckBox = findViewById(R.id.manualCheckBox);

        boolean hasFullSet = fullSetCheckBox.isChecked();
        boolean hasDisc = discCheckBox.isChecked();
        boolean hasBox = boxCheckBox.isChecked();
        boolean hasManual = manualCheckBox.isChecked();

        if (!(hasFullSet || hasDisc || hasBox || hasManual)) {
            Toast.makeText(this, "Sale must contain one item",
                    Toast.LENGTH_LONG).show();
            return;
        }


        EditText priceField = findViewById(R.id.priceField);
        String price = String.format(Locale.US,"%.2f",
                Double.parseDouble(priceField.getText().toString()));
        Date currentTime = Calendar.getInstance().getTime();
        String saleTime = new SimpleDateFormat(
                "EEE, MMM d yyyy 'at' HH:mm", Locale.US).
                format(Calendar.getInstance().getTime());


        SharedPreferences.Editor prefEdit = getSharedPreferences(
                getString(R.string.preferences_name), MODE_PRIVATE).edit();

        prefEdit.putString(getString(R.string.last_game_title),
                getIntent().getStringExtra(getString(
                        R.string.current_game_title)));
        prefEdit.putInt(getString(R.string.last_console),
                getIntent().getIntExtra(getString(
                        R.string.current_console), -1));
        prefEdit.putInt(getString(R.string.last_condition),
                conditionInt);
        prefEdit.putBoolean(getString(R.string.last_has_disc), hasDisc);
        prefEdit.putBoolean(getString(R.string.last_has_box), hasBox);
        prefEdit.putBoolean(getString(R.string.last_has_manual), hasManual);
        prefEdit.putString(getString(R.string.last_price), price);
        prefEdit.putString(getString(R.string.last_time), saleTime);

        prefEdit.apply();

        startActivity(new Intent(this, FourthActivity.class));

    }

}
