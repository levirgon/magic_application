package com.example.tapos.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapos.myapplication.Database.MagicOperations;
import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Magic;

public class NewMagicActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText detailsInput;
    private Button doneButton;
    private MagicOperations mMagicOperations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_magic);
        mMagicOperations = MagicOperations.getInstance(this);
        initializeViews();
    }

    private void initializeViews() {
        titleInput = findViewById(R.id.magic_title_input);
        detailsInput = findViewById(R.id.magic_description_input);
        doneButton = findViewById(R.id.done_button);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String description = detailsInput.getText().toString();
                createNewMagic(title, description);
            }
        });
    }

    private void createNewMagic(String title, String description) {
        mMagicOperations.addMagic(new Magic(title, description, R.drawable.magic_trick));
        finish();
    }
}
