package com.example.camerakitjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.camerakit.CameraKitView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private CameraKitView cameraKitView;
    private Button photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraKitView = findViewById(R.id.camera);
        photoButton = findViewById(R.id.photoButton);
        photoButton.setOnClickListener(photoOnClickListner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private View.OnClickListener photoOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, byte[] photo) {
                    File savedPhoto = new File(Environment.getStorageDirectory(),"photo.jpg");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(savedPhoto.getPath());
                        fileOutputStream.write(photo);
                        fileOutputStream.close();
                        //Toast.makeText(MainActivity.this, "CCCC", Toast.LENGTH_SHORT).show();
                    }catch (java.io.IOException e){
                        //Toast.makeText(MainActivity.this, "EEE", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    };
}