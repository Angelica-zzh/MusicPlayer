package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musicplayer.musiclist.MusicList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MusicList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<MusicList>();
        list.add(new MusicList("周深的歌","周深唱歌超好听",R.drawable.zhoushen));
        list.add(new MusicList("欧美点唱机","欧美经典歌曲",R.drawable.oumei));
        list.add(new MusicList("一周摇滚上新","一周摇滚歌曲推荐",R.drawable.yaogun));
        list.add(new MusicList("下午茶时间","下午茶时间最适合听的音乐",R.drawable.xiawucha));
        list.add(new MusicList("影视经典","经典电影中的插曲",R.drawable.film));

        ListView listView = (ListView)findViewById(R.id.music_list);
        listView.setAdapter(new MyAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            //如果convertView为空则需要重新创建资源视图,如果不为空则表示可以用来复用.无需再次new一个view来使用.
            if(convertView==null){
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.music_list, null);
            }else{
                view = convertView;
            }
            TextView tv_name  = (TextView)view.findViewById(R.id.list_name);
            TextView tv_des = (TextView)view.findViewById(R.id.list_dis);
            ImageView img = (ImageView)view.findViewById(R.id.list_img);

            tv_name.setText(list.get(position).getName());
            tv_des.setText(list.get(position).getDescription());
            img.setImageResource(list.get(position).getImg());

            return view;
        }
    }
}