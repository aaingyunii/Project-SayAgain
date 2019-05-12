package com.example.in_gyunahn.sayagain;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {

    SingerAdapter adapter;

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://dict.naver.com/"));
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listView);

        // 어댑터 클래스 구성 끝낸 후, 리스트뷰에 어댑터 객체를 만든 후 설정 필요
        adapter = new SingerAdapter( );
//        // 데이터 추가
//        adapter.addItem(new SingerItem("one", "(숫자)일"));
//        adapter.addItem(new SingerItem("clock", "시계"));
//        adapter.addItem(new SingerItem("police", "경찰, 경찰서"));


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext( ), "선택 :" + item.getName( ), Toast.LENGTH_SHORT).show( );
            }
        });

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                String name = editText.getText( ).toString( );
                String mobile = editText2.getText( ).toString( );
                adapter.addItem(new SingerItem(name, mobile));
                adapter.notifyDataSetChanged( ); // 이 메소드를 호출하면 어댑터 쪽에서 리스트뷰를 갱신하라 함.
            }
        });


    }


    // singer 어댑터 클래스 정의
    class SingerAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList( );

        @Override
        public int getCount() {
            return items.size( );    // 아이템의 갯수 (배열의 갯수)
        }

        // 클래스 밖에서 item data 추가하는 메소드 정의
        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) { // 아이템 선택
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {   //id값이 있다면 넘겨줘라.
            return position;                   // 특정 아이디를 만들어서 넘겨줘도 됨.
        }


        // SingerItemView(아이템뷰)를 리턴하는 메소드
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SingerItemView view = null;

            // convertView : 이전에 썼던 itemview
            if (convertView == null) {   // convertView이 null값인 경우에만 view를 새로 만듬
                view = new SingerItemView(getApplicationContext( )); // 어떤 뷰든 안드로이드는 context객체를 받음*
            } else {
                view = (SingerItemView) convertView; // 있다면 캐스팅만해서 다시 보여줌
            }

            // 아이템이 많을 때, 스크롤로 왔다갔다 하며 보여지지 않는 것은 새로 정의하지 않아도 된다.

            SingerItem item = items.get(position);
            view.setName(item.getName( ));       // name 설정
            view.setMobile(item.getMobile( ));   // mobile 설정
//            view.setImage(item.getResId());     // ResId 설정

            return view;
        }
    }
}