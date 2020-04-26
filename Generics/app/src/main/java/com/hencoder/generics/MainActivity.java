package com.hencoder.generics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hencoder.generics.fruit.Apple;
import com.hencoder.generics.fruit.Banana;
import com.hencoder.generics.fruit.Fruit;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Wrapper<String> stringWrapper = new Wrapper<>();
    stringWrapper.set("rengwuxian");
    String string = stringWrapper.get();

    HenCoderList<String> list1 = new HenCoderList<>();
    list1.add("rengwuxian");
    String name = list1.get(0);

    NonGenericList list2 = new NonGenericList();
    if ("rengwuxian" instanceof String) {
      list2.add("rengwuxian");
    }
    if (Integer.class.isInstance(1)) {
      list2.add(1);
    }
    String name2 = (String) list2.get(0);

    ArrayList<Fruit> fruits = new ArrayList<Apple>();

    ArrayList<Apple> apples = new ArrayList<Apple>();
    Banana banana = new Banana();
    apples.add(banana);

    ArrayList<Fruit> fruits1 = new ArrayList<Fruit>();
    Banana banana1 = new Banana();
    fruits1.add(banana1);

    ArrayList<Apple> apples2 = new ArrayList<Apple>();
    ArrayList<? extends Fruit> fruits2 = apples2;
    Banana banana2 = new Banana();
    fruits2.add(banana2);
    fruits2.set(0, banana2);
    Apple apple = apples2.get(0);

    Shop<? extends Fruit> shop = new Shop<Apple>() {
      @Override
      public Apple buy() {
        return null;
      }

      @Override
      public float refund(Apple item) {
        return 0;
      }
    };
    Banana banana3 = new Banana();
    Apple apple3 = new Apple();
    Fruit fruit = new Fruit() {
    };
    shop.refund(fruit);

    Fruit fruit3 = new Apple();
    fruit3 = new Banana();
    ArrayList<? extends Fruit> fruits3 = new ArrayList<Apple>();
    List<Fruit> fruits4 = new ArrayList<>();
    fruits4.add(new Apple());

    Fruit[] fruits5 = new Apple[10];
    fruits5[0] = new Banana();
    System.out.println("Fruit 数组添加元素完成！");

    ArrayList<Apple> apples6 = new ArrayList<Apple>();
    ArrayList<Fruit> fruits6 = apples6;
    fruits6.add(new Banana());
    Apple apple6 = apples6.get(0);
    System.out.println("Fruit List 添加元素完成！");

    ArrayList<? extends Fruit> fruits7 = new ArrayList<Apple>();
    float totalWeight = 0;
    for (Fruit fruit1 : fruits7) {
      totalWeight += fruit1.getWeight();
    }

    getTotalWeight(apples6);

    List<? super Apple> apples1 = new ArrayList<Fruit>();
    apples1.add(new Apple());
    Apple apple1 = apples1.get(0);

    Apple apple2 = new Apple();
    List<Apple> apples3 = new ArrayList<>();
    apples3.add(apple2);
    List<Fruit> fruits8 = new ArrayList<>();
    fruits8.add(apple2);

    apple2.addMeToList(fruits8);

    Shop<Apple> appleShop = ...;
    Tv tv = new Tv();
    Fridge fridge = new Fridge();
    List<Apple> apples4 = appleShop.recycle(tv);
    List<Apple> apples5 = appleShop.recycle(fridge);

    Tv newTv = appleShop.tradeIn(tv, 100);
    Fridge newFridge = appleShop.tradeIn(fridge, 100);

    Apple apple4 = appleShop.take();
    appleShop.<Tv>take();
    appleShop.<Apple>tradeIn(apple, 10);
    List<Apple> apples7 = appleShop.recycle(tv);

    Shop<Apple> appleShop1 = ...;
    Apple apple5 = appleShop1.buy();
    appleShop1.refund(apple5);
    appleShop1.refund(new Banana());
    NonGenericShop appleShop2 = ...;
    Apple apple7 = (Apple) appleShop2.buy();
    appleShop2.refund(apple7);
    appleShop2.refund(new Banana());

    Shop<Banana> bananaShop = ....;
    Banana banana4 = bananaShop.buy();
    bananaShop.refund(new Apple());
    AppleShop appleShop3 = ...;
    Apple apple8 = appleShop3.buy();
    appleShop3.refund(new Banana());

    AppleShop<SerializableApple> serializableAppleAppleShop;

    Shop<T>
    new ArrayList<T>();

    Shop<? super Fruit> fruitShop = new Shop<Object>() {
      @Override
      public Object buy() {
        return null;
      }

      @Override
      public float refund(Object item) {
        return 0;
      }

      @Override
      public <O> List<Object> recycle(O item) {
        return null;
      }

      @Override
      public <E> E tradeIn(E item, float money) {
        return null;
      }

      @Override
      public <R> R take() {
        return null;
      }

      @Override
      public <P> Shop<P> merge(List<P> list1, List<P> list2) {
        return null;
      }
    };
    List<? extends Shop<? extends Fruit>> shops = new ArrayList<Shop<? extends Fruit>>();

    List strings = new ArrayList();
    List;
    List<String>;
    List<Integer>;
    Object object = ...;
    if (object instanceof List<String>) {

    }
    List<String>.class;

    TypeToken<List<String>> listToken = new TypeToken<List<String>>(){};
    listToken.getRawType();

    Field field = ...;
    field.getType(); // List<String> -> List
    field.getGenericType(); // List<String> -> List<String>
    Method method = ...;
    method.getGenericReturnType();
    method.getGenericParameterTypes();

    Test test = new Test();
    try {
      Field field1 = test.getClass().getDeclaredField("list");
      field1.getGenericType();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }

    List<? extends Object> objects = new ArrayList<String>(); // covariant 协变
    List<? super String> strings2 = new ArrayList<Object>(); // contravariant 逆变 / 反变
    // Producer extends, Consumer super

    List<Shop<Apple>> appleShops = new ArrayList<Shop<Apple>>();
    Fruit[] fruits9 = new Apple[10];
  }

  float getTotalWeight(List<? extends Fruit> fruits) {
    float totalWeight = 0;
    for (Fruit fruit : fruits) {
      totalWeight += fruit.getWeight();
    }
    return totalWeight;
  }

  static <C> void filter(List<C> list) {
    for (C c : list) {
      ????;
    }
  }

  interface AppleShop<T extends Apple & Serializable> {
    T buy();

    float refund(T item);
  }
}








