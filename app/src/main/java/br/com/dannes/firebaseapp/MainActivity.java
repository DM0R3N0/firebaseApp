package br.com.dannes.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private ImageView imgFoto;
    private Button bntUpload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DatabaseReference usuarios = referencia.child("usuarios");
        //DatabaseReference produtos = referencia.child("produtos");
        FirebaseAuth user = FirebaseAuth.getInstance();

        bntUpload = findViewById(R.id.btnUpload);
        imgFoto = findViewById(R.id.imgFoto);

        // user.signOut();




        bntUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Configurar para imagens ser salva na memoria
                imgFoto.setDrawingCacheEnabled(true);
                imgFoto.buildDrawingCache();

                //Recuperar o bitmap da imagem a ser carregada
                Bitmap bitmap = imgFoto.getDrawingCache();

                //Comprimir bitmap para formato jpeg/png
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                //Converter o baos para pixel brutos em uma matriz de bytes
                //dados da imagem
                byte[] dadosImagem = baos.toByteArray();


                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("8cc83f05-9ead-4b9c-8187-9fdcdd082576jpeg");

                Glide.with(MainActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(imagemRef)
                        .into(imgFoto);

/*
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao deletar a imagem" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Imagen deletada com sucesso" ,
                                Toast.LENGTH_LONG).show();
                    }
                });

/*

                //Retorna o objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou" + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                           @Override
                           public void onComplete(@NonNull Task<Uri> task) {
                               Uri url = task.getResult();
                               Toast.makeText(MainActivity.this, "Sucesso no upload da imagem"
                                               + url.toString(),
                                       Toast.LENGTH_LONG).show();
                           }
                       });

                    }
                });*/

            }
        });


        //Configuração do realtime databasae
/*        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Gerando identificador unico no Firebase

        Usuarios usuario = new Usuarios();
        usuario.setNome("Daniel");
        usuario.setSobrenome("Moreno");
        usuario.setIdade(37);

        Usuarios usuario1 = new Usuarios();
        usuario1.setNome("Simone");
        usuario1.setSobrenome("Moreno");
        usuario1.setIdade(37);

        Usuarios usuario2 = new Usuarios();
        usuario2.setNome("Anne Elise");
        usuario2.setSobrenome("Moreno");
        usuario2.setIdade(1);

        usuarios.push().setValue(usuario);
        usuarios.push().setValue(usuario1);
        usuarios.push().setValue(usuario2);



        //DatabaseReference usuarioPesquisa = usuarios.child("-Mod1bV8oyfkEd_cA9_2");
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Daniel");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2); //Limite de busca
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("D").endAt("D" + "\uf8ff");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuarios dadosUsuario = snapshot.getValue(Usuarios.class);
                //og.i("Dados usuario", "Nome: "+ dadosUsuario.getNome() + " "+"Idade: " + dadosUsuario.getIdade());
                Log.i("Dados usuario", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
         }

        );



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

        user.createUserWithEmailAndPassword("dani2@gmail.com", "dani12345")
        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("CreateUser", "Sucesso ao cadastrar o usuário");
                }else {
                    Log.i("CreateUser", "Erro ao cadastrar o usuário");
                }
            }
        });


        //Deslogar usuario
        user.signOut();

        //Logar usuario

        user.signInWithEmailAndPassword("dani2@gmail.com", "dani12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("signIn", "Sucesso ao logar o usuário");
                }else {
                    Log.i("signIn", "Erro ao logar o usuário");
            }
        }});


        //Verificar se usuario está logado

        if (user.getCurrentUser() != null){
            Log.i("CurrentUser", "Usuário logado");
        }else{
            Log.i("CurrentUser", "Usuário não logado");
        }
*/
    }
}