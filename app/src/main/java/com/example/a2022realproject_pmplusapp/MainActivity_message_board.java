package com.example.a2022realproject_pmplusapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//게시글 메뉴 클릭 시 보여지는 화면에 관련된 소스코드 작성해주시면 됩니다.

/*
<중요>주석 지우지 마세요. 이해를 위해 코드에 대한 간략한 흐름도를 적어주세요. 그리고 코드를 작성하시면서
중간중간에 간략한 설명을 적어주세요.

흐름도 작성하는 곳


코드 작성자 : 나정민
 */
public class MainActivity_message_board extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();//파이어베이스에 연결

    private RecyclerView mMainRecyclerView;

    private MainAdapter mAdapter;
    private List<Board> mBoardList;

    Toolbar toolbar;
    ImageButton go_write_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message_board);

        mMainRecyclerView =  findViewById(R.id.board_recycler_view); //리사이클러 뷰 연결
        go_write_button = (ImageButton)findViewById(R.id.btn_go_write_messageboard);

        findViewById(R.id.btn_go_write_messageboard).setOnClickListener(this);


        mBoardList = new ArrayList<>();

        mStore.collection("board")
                .addSnapshotListener(new EventListener<QuerySnapshot>() { //실시간으로 정보 업데이트할거임
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                assert queryDocumentSnapshots != null;
                for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    String id = (String) dc.getDocument().getData().get("id");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents = (String) dc.getDocument().getData().get("contents");
                    String name = (String) dc.getDocument().getData().get("name");

                    Board data = new Board(id, title, contents, name);

                    mBoardList.add(0,data); //역순으로 출력(최신순대로 출력하겠다는거)
                }
                mAdapter = new MainAdapter(mBoardList);
                mMainRecyclerView.setAdapter(mAdapter);
            }
        });

        toolbar = (Toolbar)findViewById(R.id.toolbar_messageboard);
        setSupportActionBar(toolbar); //툴바를 불러오고

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_chevron_left_white_24dp);
        getSupportActionBar().setTitle("항만/항구 정보 공유 게시판");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //뒤로가기 했을 때
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super. onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity_write_in_message_board.class));
    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

        private List<Board> mBoardList;

        private MainAdapter(List<Board> mBoardList) {
            this.mBoardList = mBoardList;
        }


        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()). inflate(R.layout.item_main_board, parent,false));
        //item_main_board 레이아웃의 parent를 가져옴
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            Board data = mBoardList.get(position);
            holder.mTitleTextView.setText(data.getTitle());
            holder.mNameTextView.setText(data.getName());
            holder.mContentTextView.setText(data.getContent());
        }

        @Override
        public int getItemCount() {
            return mBoardList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder{

            private TextView mTitleTextView;
            private TextView mNameTextView;
            private TextView mContentTextView;

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);

                mTitleTextView = itemView.findViewById(R.id.item_title_text);
                mNameTextView = itemView.findViewById(R.id.item_name_text);
                mContentTextView = itemView.findViewById(R.id.item_content_text); //게시글 내용

            }
        }
    }
}