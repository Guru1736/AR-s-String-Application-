package com.Athrav.ars6strings;

import android.app.Application;
import android.media.MediaController2;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class viewmodel extends RecyclerView.ViewHolder {


    TextView vtitleview ;
    VideoView vw ;


    public viewmodel(@NonNull View itemView) {
        super(itemView);
        vtitleview = itemView.findViewById(R.id.vtitle);
        vw = itemView.findViewById(R.id.vidvw);


    }

     public void videos( String title , String url )
    {
        vtitleview.setText(title);
        Uri videoURI = Uri.parse(url);
        vw.setVideoURI(videoURI);
     MediaController mediaController = new MediaController(vw.getContext());
        vw.setMediaController(mediaController);
        mediaController.setAnchorView(vw);
        if (vw.isPlaying())
        {
            vw.pause();
        }
        else
        {
            vw.start();
        }




    }

}
