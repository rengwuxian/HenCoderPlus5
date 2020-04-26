package com.hencoder.apt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hencoder.lib.Binding;
import com.hencoder.lib_annotations.BindView;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.textView) TextView textView;
  @BindView(R.id.layout) ViewGroup layout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Binding.bind(this);
    textView.setText("HenCoder");
    layout.setBackgroundColor(Color.CYAN);
  }
}