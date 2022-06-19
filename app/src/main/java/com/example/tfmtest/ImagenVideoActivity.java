package com.example.tfmtest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tfmtest.utils.Loading;

import java.io.File;

public class ImagenVideoActivity extends AppCompatActivity {
    byte[] attachmentByte;
    String attachmentString, type;
    ImageView ivAttachment;
    VideoView videoView;
    int postionMinutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_image);
        ivAttachment = findViewById(R.id.ivAttachment);
        videoView = findViewById(R.id.vvAttachment);

        Bundle extras = getIntent().getExtras();
        if (extras.getBoolean("isVideo")) {
            type = "video";
            Loading.showLoading(this, "Cargando...");
            ivAttachment.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            MediaController mediaController = new MediaController(ImagenVideoActivity.this);
            mediaController.setAnchorView(videoView);
            Uri uri = Uri.parse(extras.getString("video"));

            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Loading.hideLoading();
                    if (postionMinutes > 0) {
                        videoView.seekTo(postionMinutes);
                    } else {
                        videoView.seekTo(0);
                    }
                }
            });

            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Loading.hideLoading();
                    return false;
                }
            });

        } else {
            type = "image";
            ivAttachment.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);

            if (extras.get("imagen") != null) {
                Uri uri = Uri.fromFile(new File(extras.getString("imagen")));
                Glide.with(this)
                        .load(uri)
                        .apply(new RequestOptions().centerCrop())
                        .into(ivAttachment);
                ivAttachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        if (Build.VERSION.SDK_INT < 24) {
                            intent.setDataAndType(Uri.fromFile(new File(extras.getString("imagen"))), "image/*");
                        } else {
                            intent.setDataAndType(Uri.parse(uri.getPath()), "image/*");
                        }
                        startActivity(intent);
                    }
                });
            }
        }
    }

}
