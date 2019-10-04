package com.tec.aoacalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextInputEditText codeTextInputEditTExt;
    TextInputEditText nameTextInputEditText;
    TextInputEditText phoneTextInputEditText;
    TextInputEditText balanceInputEditText;

    private int indicator;

    List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    protected void initComponents(){
        codeTextInputEditTExt   = findViewById(R.id.codeMainTextInputEditTExt);
        nameTextInputEditText   = findViewById(R.id.nameMainTextInputEditText);
        phoneTextInputEditText  = findViewById(R.id.phoneMainTextInputEditText);
        balanceInputEditText    = findViewById(R.id.balanceMainInputEditText);
        Button addButton        = findViewById(R.id.addMainButton);
        Button deleteButton     = findViewById(R.id.deleteMainButton);
        Button nextButton       = findViewById(R.id.nextMainButton);
        Button previousButton   = findViewById(R.id.previousMainButton);
        Button firtsButton      = findViewById(R.id.firtsMainButton);
        Button lastestButton    = findViewById(R.id.lastestMainButton);
        Button clearButton      = findViewById(R.id.clearMainButton);

        indicator   = -1;
        data        = new ArrayList<>();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.size() == 10){
                    Snackbar.make(v,"Se alcanzo el limine de registros",Snackbar.LENGTH_LONG).show();
                    return;
                }

                Map<String, String> currentData = new HashMap<>();

                String code     = codeTextInputEditTExt.getText().toString();
                String name     = nameTextInputEditText.getText().toString();
                String phone    = phoneTextInputEditText.getText().toString();
                double balance  = Double.parseDouble(balanceInputEditText.getText().toString());


                if (code.isEmpty()){
                    Snackbar.make(v,"Ingrese la clave",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (name.isEmpty()){
                    Snackbar.make(v,"Ingrese el nombre",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (phone.isEmpty()){
                    Snackbar.make(v,"Ingrese el telefono",Snackbar.LENGTH_LONG).show();
                    return;
                }

                currentData.put("code",code);
                currentData.put("name",name);
                currentData.put("phone",phone);
                currentData.put("balance",String.valueOf(balance));

                data.add(currentData);

                clearForm();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indicator == -1){
                    Snackbar.make(v,"No hay ningun registro",Snackbar.LENGTH_LONG).show();
                }else {
                    data.remove(indicator);
                    clearForm();
                    Snackbar.make(v,"Registro eliminado",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 0){

                    if (indicator == (data.size()-1)){
                        Snackbar.make(v,"Este es el ultimo registro",Snackbar.LENGTH_LONG).show();
                    }else{
                        indicator++;
                        setForm(data.get(indicator));
                    }

                }else
                    Snackbar.make(v,"No hay ningun registro",Snackbar.LENGTH_LONG).show();

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 0){

                    if (indicator == 0){
                        Snackbar.make(v,"Este es el primer registro",Snackbar.LENGTH_LONG).show();
                    }else{

                        indicator--;
                        setForm(data.get(indicator));
                    }

                }else
                    Snackbar.make(v,"No hay ningun registro",Snackbar.LENGTH_LONG).show();

            }
        });

        firtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.size() > 0){
                    indicator   = 0;
                    setForm(data.get(indicator));
                }else
                    Snackbar.make(v,"No hay ningun registro",Snackbar.LENGTH_LONG).show();

            }
        });

        lastestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 0) {
                    indicator = data.size() - 1;
                    setForm(data.get(indicator));
                }else
                    Snackbar.make(v,"No hay ningun registro",Snackbar.LENGTH_LONG).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();

            }
        });
    }

    protected void setForm(Map<String, String> currentData){
        codeTextInputEditTExt.setText(currentData.get("code"));
        nameTextInputEditText.setText(currentData.get("name"));
        phoneTextInputEditText.setText(currentData.get("phone"));
        balanceInputEditText.setText(currentData.get("balance"));
    }

    protected void clearForm(){
        codeTextInputEditTExt.setText("");
        nameTextInputEditText.setText("");
        phoneTextInputEditText.setText("");
        balanceInputEditText.setText("0");

        indicator   = -1;
    }
}
