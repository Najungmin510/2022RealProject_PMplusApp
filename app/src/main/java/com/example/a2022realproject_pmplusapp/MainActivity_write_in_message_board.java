package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//게시글 작성 화면 관련 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */

public class MainActivity_write_in_message_board extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private EditText mWriteTitleText;
    private EditText mWriteContentsText;
    private String id;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_write_in_message_board);
        toolbar = (Toolbar)findViewById(R.id.toolbar_message_write_in_messageboard);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("게시글 작성");

        findViewById(R.id.write_upload).setOnClickListener(this);


        mWriteTitleText = (EditText)findViewById(R.id.write_title_text);
        mWriteContentsText = (EditText)findViewById(R.id.write_content_text);

    }

    @Override
    public void onClick(View v){
        id=mStore.collection("board").document().getId(); //db에서 id를 얻어옴
        Map<String, Object> post = new HashMap<>();
        post.put("id",id);
        post.put("title",mWriteTitleText.getText().toString());
        post.put("content",mWriteContentsText.getText().toString());
        post.put("name","관리자");

        String tit = mWriteTitleText.getText().toString();
        String con = mWriteContentsText.getText().toString();

       if(tit.equals("")){
           Toast.makeText(MainActivity_write_in_message_board.this,"제목을 입력해주세요.",Toast.LENGTH_SHORT).show();

       } else if(con.equals("")) {
           Toast.makeText(MainActivity_write_in_message_board.this,"내용을 입력해주세요.",Toast.LENGTH_SHORT).show();

       } else {

           mStore.collection("board").document(id).set(post).addOnSuccessListener(new OnSuccessListener<Void>() {

               @Override
               public void onSuccess(Void unused) {
                   Toast.makeText(MainActivity_write_in_message_board.this,"업로드 되었습니다.",Toast.LENGTH_SHORT).show();
                   finish();
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(MainActivity_write_in_message_board.this,"업로드에 실패했습니다.",Toast.LENGTH_SHORT).show();
               }
           });

       }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super. onOptionsItemSelected(item);
    }
}