package be.ehb.demoinput;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etNaam, etEmail;
    private RadioGroup rgGeslacht;
    private Switch swiAkkoord;
    private Button btnBevestig, btnDatum;
    private Calendar geboorteDatum;

//listener, wat doen bij clicks
    private View.OnClickListener bevestigListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //iets in de logs wegschrijven
            Log.i("TEST", etNaam.getText().toString());
            Log.i("TEST", geboorteDatum.getTime().toString());
            //extra dateformatter
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            String datumAlsTekst = sdf.format(geboorteDatum.getTime());
            btnDatum.setText(datumAlsTekst);

            switch (rgGeslacht.getCheckedRadioButtonId()){
                case R.id.rb_man :
                    Log.i("TEST", "Man");
                    break;
                case R.id.rb_vrouw :
                    Log.i("TEST", "Vrouw");
                    break;
                case R.id.rb_ander :
                    Log.i("TEST", "Ander");
                    break;
                default: Log.i("TEST","Niks gekozen");
            }

        }
    };
    private View.OnClickListener datumListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toonKalenderDialoog();
        }
    };
    private void toonKalenderDialoog(){
        geboorteDatum = Calendar.getInstance();

        DatePickerDialog geboorteDatumPicker = new DatePickerDialog(this, null, geboorteDatum.get(Calendar.YEAR), geboorteDatum.get(Calendar.MONTH), geboorteDatum.get(Calendar.DAY_OF_MONTH));
        geboorteDatumPicker.show();

        DatePickerDialog.OnDateSetListener datumListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                geboorteDatum.set(Calendar.YEAR, year);
                geboorteDatum.set(Calendar.MONTH, month);
                geboorteDatum.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            }
        };

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//verwijzingen naar componenten in het scherm
        etNaam = findViewById(R.id.et_naam);
        etEmail = findViewById(R.id.et_email);
        rgGeslacht = findViewById(R.id.rg_geslacht);
        swiAkkoord = findViewById(R.id.swi_akkoord);
        btnBevestig =findViewById(R.id.btn_bevestig);
        btnDatum = findViewById(R.id.btn_datum);
        //listeners aan views hangen
        btnBevestig.setOnClickListener(bevestigListener);
        btnDatum.setOnClickListener(datumListener);
    }
}
