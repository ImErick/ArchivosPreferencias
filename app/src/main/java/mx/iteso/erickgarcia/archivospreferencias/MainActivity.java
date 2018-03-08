package mx.iteso.erickgarcia.archivospreferencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView txt_marca, txt_modelo;
    Button btn_guardar, btn_consultar, btn_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_marca = (TextView) findViewById(R.id.txt_marca);
        txt_modelo = (TextView) findViewById(R.id.txt_modelo);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            HashMap<String, String> map = new HashMap<>();

            @Override
            public void onClick(View view) {
                if (!txt_marca.equals("") && !txt_modelo.equals("")){
                    //new Metodos(MainActivity.this).SharePreferenceMethod("Autos", "marca", txt_marca.getText().toString());
                    //new Metodos(MainActivity.this).SharePreferenceMethod("Autos", "modelo", txt_modelo.getText().toString());
                    map.put(txt_marca.getText().toString(), txt_modelo.getText().toString());
                    if (new Metodos(MainActivity.this).SharePreferenceMethod("Autos", map)) {
                        new Metodos(MainActivity.this).ToastMessage("datos guardados correctamente");
                        txt_marca.setText("");
                        txt_modelo.setText("");
                        txt_marca.requestFocus();
                    } else
                        new Metodos(MainActivity.this).ToastMessage("error al guardar dude");
                } else {
                    new Metodos(MainActivity.this).ToastMessage("favor de ingresar los datos solicitados");
                }
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_modelo.setText(new Metodos(MainActivity.this).getValue("Autos", txt_marca.getText().toString()));
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new Metodos(MainActivity.this).delKey("Autos", txt_marca.getText().toString())) {
                    new Metodos(MainActivity.this).ToastMessage("registro eliminado correctamente");
                    txt_marca.setText("");
                    txt_marca.requestFocus();
                } else
                    new Metodos(MainActivity.this).ToastMessage("no hay nada que eliminar");
            }
        });
    }
}
