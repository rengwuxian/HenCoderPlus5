package com.hencoder.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import okio.BufferedSource;
import okio.Okio;

public class Main {
  public static void main(String[] args) {
//    io1();
//    io2();
//    io3();
//    io4();
//    io5();
//    io6();
//    io7();
//    io8();
//    io9();
    io10();
  }

  private static void io1() {
    try (OutputStream outputStream = new FileOutputStream("./io/text.txt")) {
      outputStream.write('a');
      outputStream.write('b');
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io2() {
    try (InputStream inputStream = new FileInputStream("./io/text.txt")) {
      System.out.println((char)inputStream.read());
      System.out.println((char)inputStream.read());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io3() {
    try (InputStream inputStream = new FileInputStream("./io/text.txt");
         Reader reader = new InputStreamReader(inputStream);
         BufferedReader bufferedReader = new BufferedReader(reader)) {
      System.out.println(bufferedReader.readLine());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io4() {
    try (OutputStream outputStream = new FileOutputStream("./io/text.txt");
         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
      bufferedOutputStream.write('a');
      bufferedOutputStream.write('q');
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io5() {
    try (InputStream inputStream = new BufferedInputStream(new FileInputStream("./io/text.txt"));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("./io/new_text.txt"))) {
      byte[] data = new byte[1024];
      int read;
      while ((read = inputStream.read(data)) != -1) {
        outputStream.write(data, 0, read);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io6() {
    try (Socket socket = new Socket("hencoder.com", 80);
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      writer.write("GET / HTTP/1.1\n" +
          "Host: www.example.com\n\n");
      writer.flush();
      String message;
      while ((message = reader.readLine()) != null) {
        System.out.println(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io7() {
    try (ServerSocket serverSocket = new ServerSocket(80);
         Socket socket = serverSocket.accept();
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      writer.write("HTTP/1.1 200 OK\n" +
          "Date: Mon, 23 May 2005 22:38:34 GMT\n" +
          "Content-Type: text/html; charset=UTF-8\n" +
          "Content-Length: 138\n" +
          "Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT\n" +
          "Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)\n" +
          "ETag: \"3f80f-1b6-3e1cb03b\"\n" +
          "Accept-Ranges: bytes\n" +
          "Connection: close\n" +
          "\n" +
          "<html>\n" +
          "  <head>\n" +
          "    <title>An Example Page</title>\n" +
          "  </head>\n" +
          "  <body>\n" +
          "    <p>Hello World, this is a very simple HTML document.</p>\n" +
          "  </body>\n" +
          "</html>\n\n");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io8() {
    try {
      RandomAccessFile file = new RandomAccessFile("./io/text.txt", "r");
      FileChannel channel = file.getChannel();
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
      channel.read(byteBuffer);
      byteBuffer.flip();
      System.out.println(Charset.defaultCharset().decode(byteBuffer));
      byteBuffer.clear();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io9() {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress(80));
      serverSocketChannel.configureBlocking(false);
      Selector selector = Selector.open();
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      while (true) {
        selector.select();
        for (SelectionKey key : selector.selectedKeys()) {
          if (key.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) != -1) {
              byteBuffer.flip();
              socketChannel.write(byteBuffer);
              byteBuffer.clear();
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void io10() {
    try (BufferedSource source = Okio.buffer(Okio.source(new File("./io/text.txt")))) {
      System.out.println(source.readUtf8Line());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) { // AIO Asynchronous I/O
      e.printStackTrace();
    }
  }

}