package com.example.musicplayer.Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.musicplayer.musiclist.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadUtils {
    public static ArrayList<Song> list;

    public static Song song;


    public static ArrayList<Song> getmusic(Context context) {

        list =new ArrayList<>();


        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                ,null,null,null, MediaStore.Audio.AudioColumns.IS_MUSIC);

        if (cursor !=null) {
            while (cursor.moveToNext()) {

                song =new Song();
                song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

//                把歌曲名字和歌手切割开
                if (song.size >1000 *800) {
                    if (song.song.contains("-")) {
                        String[] str = song.song.split("-");
                        song.singer = str[0];
                        song.song = str[1];
                    }
                    list.add(song);
                }

            }
        }

        cursor.close();
        return list;

    }
    //格式化歌曲时间
    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date data = new Date(time);
        return dateFormat.format(data);
    }
}
