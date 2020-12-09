package mx.edu.utng.example.app_firebase_pedro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText TemaM;
    Spinner spiA;
    Spinner spiS;
    Button btnReg;

    private DatabaseReference DBPedro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TemaM = (EditText)findViewById(R.id.etTema);
        spiA = (Spinner) findViewById(R.id.spiArea);
        spiS = (Spinner) findViewById(R.id.spiSeccion);
        btnReg = (Button) findViewById(R.id.btnRegistrar);

        DBPedro = FirebaseDatabase.getInstance().getReference("Clases" );

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarClase();
            }
        });
    }

    //Metodo Registro
    public void registrarClase(){
        String seccion = spiS.getSelectedItem().toString();
        String area = spiA.getSelectedItem().toString();
        String tema = TemaM.getText().toString();

        if (!TextUtils.isEmpty(tema)){
            String id = DBPedro.push().getKey();
            Clases leccion = new Clases(id,seccion,area,tema);
            DBPedro.child("Leccion").child(id).setValue(leccion);
            Toast.makeText(this, "Clase Registrada",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Debe introducir un tema",Toast.LENGTH_LONG).show();
        }
    }
}