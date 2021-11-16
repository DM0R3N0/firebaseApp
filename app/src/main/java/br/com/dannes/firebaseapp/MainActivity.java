package br.com.dannes.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");
        FirebaseAuth user = FirebaseAuth.getInstance();


        //Configuração do realtime databasae
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


/*
        //Cadastro de usuarios
        Usuarios usuario = new Usuarios();
        usuario.setNome("Daniel Francisco");
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

        //Cadastro de produtos
        Produtos produto = new Produtos();
        produto.setDescricao("Notebook");
        produto.setMarca("Lenovo");
        produto.setModelo("L1000");
        produto.setPreco(3500);

        produtos.child("01").setValue(produto);

        //Cadastro de acesso

        user.createUserWithEmailAndPassword("dani2@gmail.com", "dani12345").addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("CreateUser", "Sucesso ao cadastrar o usuário");
                }else {
                    Log.i("CreateUser", "Erro ao cadastrar o usuário");
                }
            }
        });
*/

        //Verificar se usuario está logado

        if (user.getCurrentUser() != null){
            Log.i("CreateUser", "Usuário logado");
        }else{
            Log.i("CreateUser", "Usuário não logado");
        }


    }
}