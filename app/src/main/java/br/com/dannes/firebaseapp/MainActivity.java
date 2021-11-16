package br.com.dannes.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference usuarios = referencia.child("usuarios");


        Usuarios usuario = new Usuarios();
        usuario.setNome("Daniel");
        usuario.setSobrenome("Moreno");
        usuario.setIdade(37);

        Usuarios usuario2 = new Usuarios();
        usuario2.setNome("Simone");
        usuario2.setSobrenome("Moreno");
        usuario2.setIdade(37);

        Usuarios usuario3 = new Usuarios();
        usuario3.setNome("Anne");
        usuario3.setSobrenome("Elise");
        usuario3.setIdade(1);

        usuarios.child("01").setValue(usuario);
        usuarios.child("02").setValue(usuario2);
        usuarios.child("03").setValue(usuario3);

    }
}