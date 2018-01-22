package com.example.tapos.myapplication.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Magic;

public class DetailActivity extends AppCompatActivity {

    private Magic mMagic;
    private TextView titleText;
    private TextView detailText;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty())
            mMagic = (Magic) bundle.getSerializable("magic");
        initializeViews();
    }

    private void initializeViews() {
        titleText = findViewById(R.id.magic_title);
        detailText = findViewById(R.id.magic_details);
        mImageView = findViewById(R.id.magic_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        titleText.setText(mMagic.getTitle());
        detailText.setText(mMagic.getDescription());
        Glide.with(this).load(mMagic.getThumbnail()).into(mImageView);
    }
}
