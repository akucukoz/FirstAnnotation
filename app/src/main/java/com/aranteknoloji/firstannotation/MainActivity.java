package com.aranteknoloji.firstannotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aranteknoloji.annotations.BindView;
import com.aranteknoloji.annotations.OnClick;
import com.aranteknoloji.binder.Binding;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_text)
    TextView textView;

    private final String[] strings = {"First", "Second", "Third", "Fourth", "Fifth"};
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding.bind(this);
    }

    @OnClick(R.id.main_btn)
    void onClick(View view) {
        textView.setText(strings[counter++ % 5]);
    }
}
