package mx.iteso.erickgarcia.archivospreferencias;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by erickgarcia on 07/03/18
 */

public class Metodos {
    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Metodos(Activity activity){
        this.activity = activity;
    }

    public boolean SharePreferenceMethod(String keyShared, Map<String, String> map){
        boolean esExitoso = true;
        sharedPreferences = activity.getSharedPreferences(keyShared, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        for (Map.Entry<String, String> entry: map.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
            if (editor.commit())
                continue;
            else {
                esExitoso = false;
                break;
            }
        }
        return esExitoso;
    }

    public void ToastMessage(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public String getValue(String keyShared, String key) {
        sharedPreferences = activity.getSharedPreferences(keyShared, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "no existe valor");
    }

    public boolean delKey(String keyShared, String key){
        boolean existe = true;
        sharedPreferences = activity.getSharedPreferences(keyShared, Context.MODE_PRIVATE);

        if (getValue(keyShared, key).equals("no existe valor"))
            existe = false;
        else {
            editor = sharedPreferences.edit();
            editor.remove(key);
            editor.commit();
            existe = true;
        }
        return existe;
    }

    // lo woa necesitar para la tarea
    public boolean clearAll(String keyShared) {
        sharedPreferences = activity.getSharedPreferences(keyShared, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }
}
