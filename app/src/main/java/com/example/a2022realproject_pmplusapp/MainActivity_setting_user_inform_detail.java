package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

public class MainActivity_setting_user_inform_detail extends AppCompatActivity {
    private static final String TAG = "setting_user_inform";
    private ImageView profileView;
    private String profilePath;

    ImageButton setbutton;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_user_inform_detail);

        setbutton = (ImageButton)findViewById(R.id.btn_setting_user_inform);
        setbutton.setOnClickListener(onClickListener);

        toolbar = (Toolbar) findViewById(R.id.toolbar_user_inform_detail); //툴바 선언
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.before);
        getSupportActionBar().setTitle("회원정보 입력 및 수정");

        findViewById(R.id.profile_imageView).setOnClickListener(onClickListener);

        profileView = findViewById(R.id.profile_imageView);

    }

    @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0: {
                if(resultCode == Activity.RESULT_OK){
                    profilePath = data.getStringExtra("profilePath");
                    Log.e("로그:","프로파일"+profilePath);
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    profileView.setImageBitmap(bmp);

                }
            }
            break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_setting_user_inform) { //버튼 클릭 시 세팅 함수 실행하도록 함
                try {
                    setting();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if(v.getId() == R.id.profile_imageView){
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    };


    public void setting() throws FileNotFoundException {
        final String name = ((EditText)findViewById(R.id.et_setting_user_name)).getText().toString();
        final String address = ((EditText)findViewById(R.id.et_setting_user_address)).getText().toString();
        final String phone = ((EditText)findViewById(R.id.et_setting_phone)).getText().toString();
        final String birthday = ((EditText)findViewById(R.id.et_setting_user_Date)).getText().toString();


        if(name.length() > 0 && phone.length() > 9 && birthday.length() > 5 && address.length() > 0){

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference mountainImagesRef = storageRef.child("users/"+user.getUid()+"/profileimage.jpg");

            try{
                InputStream stream = new FileInputStream(new File(profilePath));
                UploadTask uploadTask= mountainImagesRef.putStream(stream);
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            Log.e("카메라 2 basic","사진 저장 실패");
                            throw Objects.requireNonNull(task.getException());
                        }
                        return mountainImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            Uri downloadUri = task.getResult();
                            Log.e("카메라 2 basic","사진 저장 완료"+downloadUri);

                            FirebaseFirestore db = FirebaseFirestore.getInstance(); //DB 초기화
                            UserInfo userInfo = new UserInfo(name, birthday, phone, address, downloadUri.toString());
                        } else{
                            Log.e("카메라 2 basic","사진 저장 실패");
                        }
                    }
                });


            }catch(FileNotFoundException e){
                Log.e("로그","에러"+e.toString());
            }
        } else{
            setting_msg("정보를 입력해주세요");
        }

    }

    public void setting_msg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){ //뒤로가기 했을 때
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}