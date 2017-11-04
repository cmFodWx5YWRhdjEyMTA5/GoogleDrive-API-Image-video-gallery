package com.expose.drive;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.expose.drive.model.Model;

import java.util.List;

public class Details extends AppCompatActivity {

    Button button;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        button=(Button)findViewById(R.id.button);
        videoView=(VideoView)findViewById(R.id.videoview);

        String URL= getIntent().getExtras().getString("DATA");

        videoView = (VideoView) findViewById(R.id.videoview);
        Uri uri = Uri.parse("http://www.youtube.com/watch?v=2zNSgSzhBfM");
        videoView.setVideoURI(uri);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });



    }
}
