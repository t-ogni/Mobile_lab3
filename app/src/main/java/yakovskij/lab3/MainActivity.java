package yakovskij.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner fromUnit, toUnit;
    RadioGroup rg;
    EditText fromValue;
    TextView toValue;

    ArrayAdapter<Units> units, vols, sqrs, bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromUnit = findViewById(R.id.FromUnit);
        fromValue = findViewById(R.id.FromValue);
        toUnit = findViewById(R.id.ToUnit);
        toValue = findViewById(R.id.ToValue);

        rg = findViewById(R.id.Radios);

        units = new ArrayAdapter<Units>(this, android.R.layout.simple_list_item_1);
        sqrs = new ArrayAdapter<Units>(this, android.R.layout.simple_list_item_1);
        vols = new ArrayAdapter<Units>(this, android.R.layout.simple_list_item_1);
        bytes = new ArrayAdapter<Units>(this, android.R.layout.simple_list_item_1);

        units.add(new Units("мм.", 0.001));
        units.add(new Units("см.", 0.01));
        units.add(new Units("дм.", 0.1));
        units.add(new Units("м.", 1));
        units.add(new Units("км.", 1000));


        sqrs.add(new Units("мм.2", 1e-6));
        sqrs.add(new Units("см.2", 1e-4));
        sqrs.add(new Units("дм.2", 1e-2));
        sqrs.add(new Units("м.2", 1));
        sqrs.add(new Units("км.2", 1e2));


        vols.add(new Units("мм.3", 1e-9));
        vols.add(new Units("см.3", 1e-6));
        vols.add(new Units("дм.3", 1e-3));
        vols.add(new Units("м.3", 1));
        vols.add(new Units("км.3", 1e3));


        bytes.add(new Units("Б", 0.0009765625));
        bytes.add(new Units("КБ", 1));
        bytes.add(new Units("МБ", 1024));
        bytes.add(new Units("ГБ", 1024*1024));
        bytes.add(new Units("ТБ", 1024*1024*1024));

        fromUnit.setAdapter(units);
        toUnit.setAdapter(units);
    }

    public void on_change_radio(View v) {
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        
        if (rb.getText().equals("Байты")) {
            fromUnit.setAdapter(bytes);
            toUnit.setAdapter(bytes);
        } else if (rb.getText().equals("Дистанция")) {
            fromUnit.setAdapter(units);
            toUnit.setAdapter(units);
        } else if (rb.getText().equals("Площадь")) {
            fromUnit.setAdapter(sqrs);
            toUnit.setAdapter(sqrs);
        } else if (rb.getText().equals("Объем")) {
            fromUnit.setAdapter(vols);
            toUnit.setAdapter(vols);
        }
    }

    @SuppressLint("DefaultLocale")
    public void on_convert(View v) {
        Units ObjUnitFrom = (Units) fromUnit.getSelectedItem();
        Units ObjUnitTo = (Units) toUnit.getSelectedItem();

        try {
            double from = Double.parseDouble(fromValue.getText().toString());
            double to = from * ObjUnitFrom.coeff / ObjUnitTo.coeff;
            toValue.setText(String.format("%.12f", to).replaceAll("0*$", "").replaceAll("\\.$", ""));
        } catch (Exception err) {
            Toast.makeText(this, "Error: Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}