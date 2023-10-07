package com.summer.a.web.socket;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketHelper {

    private String host = "www.summerviwox.com";
    private int port = 80;
    private String data = "111111111111222222222222223333333333333333333444444444444444444555555555555";

    public void test() {
        try {
            Socket socket = new Socket(host, port);
            socket.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            while (-1 != inputStream.read(bytes)) {
                stringBuffer.append(bytes);
            }
            stringBuffer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void test1() {
        Socket socket = null;
        try {
            long t1 = System.currentTimeMillis();
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 10000);
            LogUtils.e(System.currentTimeMillis() - t1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void testAsync() {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<String>() {
            @Override
            public String doInBackground() throws Throwable {
                test1();
                return null;
            }

            @Override
            public void onSuccess(String result) {

            }
        });
    }
}
