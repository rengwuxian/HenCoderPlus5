package com.hencoder.pluginablehotfix;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

public class HotfixApp extends Application {
  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    File apk = new File(getCacheDir() + "/hotfix.dex");

    if (apk.exists()) {
      try {
        ClassLoader originalLoader = getClassLoader();
        DexClassLoader classLoader = new DexClassLoader(apk.getPath(), getCacheDir().getPath(), null, null);
        Class loaderClass = BaseDexClassLoader.class;
        Field pathListField = loaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathListObject = pathListField.get(classLoader);

        Class pathListClass = pathListObject.getClass();
        Field dexElementsField = pathListClass.getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElementsObject = dexElementsField.get(pathListObject);

        Object originalPathListObject = pathListField.get(originalLoader);
        Object originalDexElementsObject = dexElementsField.get(originalPathListObject);

        int oldLength = Array.getLength(originalDexElementsObject);
        int newLength = Array.getLength(dexElementsObject);
        Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
        for (int i = 0; i < newLength; i++) {
          Array.set(concatDexElementsObject, i, Array.get(dexElementsObject, i));
        }
        for (int i = 0; i < oldLength; i++) {
          Array.set(concatDexElementsObject, newLength + i, Array.get(originalDexElementsObject, i));
        }
        dexElementsField.set(originalPathListObject, concatDexElementsObject);

        // originalLoader.pathList.dexElements = classLoader.pathList.dexElements;
        // originalLoader.pathList.dexElements += classLoader.pathList.dexElements;
      } catch (NoSuchFieldException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }
}
