package com.example.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.musicplayer.Utils.LoadUtils;
import com.example.musicplayer.musiclist.Song;

import java.util.ArrayList;

public class ListActivity extends Activity {
    ArrayList<Song> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        arrayList = LoadUtils.getmusic(this);

        ListView listView  = findViewById(R.id.song);
        listView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(convertView==null){
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.song_message, null);
            }else{
                view = convertView;
            }
            TextView tv_position  = (TextView)view.findViewById(R.id.t_postion);
            TextView tv_song  = (TextView)view.findViewById(R.id.t_song);
            TextView tv_singer  = (TextView)view.findViewById(R.id.t_singer);
            TextView tv_duration  = (TextView)view.findViewById(R.id.t_duration);


            tv_position.setText(position+1+"");
            tv_song.setText(arrayList.get(position).song);
            tv_singer.setText(arrayList.get(position).singer);
            String time = LoadUtils.formatTime(arrayList.get(position).duration);

            tv_duration.setText(time);
            return view;
        }
    }
}
