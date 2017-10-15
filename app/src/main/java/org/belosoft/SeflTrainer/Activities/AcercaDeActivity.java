package org.belosoft.SeflTrainer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.belosoft.SeflTrainer.R;

public class AcercaDeActivity extends AppCompatActivity {

    private Button buttonAcercaDeTestInicial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        buttonAcercaDeTestInicial = (Button) findViewById(R.id.buttonAcercaDeTestInicial);

        buttonAcercaDeTestInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TestResistencia.class);
                startActivity(intent);
            }
        });
    }
}
