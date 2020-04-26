package com.hencoder.pluginable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    File apk = new File(getCacheDir() + "/plugin.apk");
    try (Source source = Okio.source(getAssets().open("apk/plugin.apk"));
         BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
      sink.writeAll(source);
    } catch (IOException e) {
      e.printStackTrace();
    }
    DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
    try {
      Class utilsClass = classLoader.loadClass("com.hencoder.plugin.Utils");
      Constructor utilsConstructor = utilsClass.getDeclaredConstructors()[0];
      utilsConstructor.setAccessible(true);
      Object utils = utilsConstructor.newInstance();
      Method shoutMethod = utilsClass.getDeclaredMethod("shout");
      shoutMethod.setAccessible(true);
      shoutMethod.invoke(utils);
      Intent intent = new Intent();
      intent.setClassName("com.hencoder.plugin", "com.hencoder.plugin.MainActivity");
      startActivity(intent);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}