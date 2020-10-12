package com.example.electricity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElectricityApplicationTests {

    @Test
    void contextLoads() {
        String aa="/D:/apache-tomcat-7.0.94-8082/webapps/electricity/WEB-INF/classes/";
        String electricity = aa.substring(0,aa.lastIndexOf("electricity"));
        System.out.println(electricity);
    }
 /*   public static void main(String[] args) throws Exception {
        *//*Socket socket = new Socket("127.0.0.1",9000);
//向服务器端程序发送数据
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("hello server, can you receiver my data?");
        bufferedWriter.flush();
        bufferedWriter.close();
        socket.close();
        System.out.println("finish send data!!");*//*

        System.out.println(5%5);
    }*/



 /*   ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    *//**	 * 超时时间/分钟	 *//*
    public static final int TIMEOUT = 5;
    public void testFixedThreadPool() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("applNo", "666");
        list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("applNo", "667");
        list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("applNo", "668");
        list.add(map3);
        List<Callable<Boolean>> callableList = new ArrayList<Callable<Boolean>>();
        if (list.size() > 0) {
            System.out.println("执行次数：" + list.size());
            for (final Map map : list) {
                Callable<Boolean> call = new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        try {
                            return doYouMethod();
                        } catch (Exception e) {
                            System.out.println("执行异常：" + e);
                            return null;
                        }
                    }
                };
                callableList.add(call);
            }
        }
        try {
            List<Future<Boolean>> futureList = fixedThreadPool.invokeAll(callableList);
            for (Future<Boolean> future : futureList) {
                Boolean flag = future.get(TIMEOUT, TimeUnit.MINUTES);
                if (flag) {
                    System.out.println(" 成功");
                } else {
                    System.out.println(" 失败");
                }
            }

        } catch (Exception e) {
            System.out.println(" ----- 异常 -----" + e.getMessage());
        }
    }
    public Boolean doYouMethod() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("执行你的方法");
        return true;
    }
    public static void main(String[] args) {
        ElectricityApplicationTests ft = new ElectricityApplicationTests();
        ft.testFixedThreadPool();
    }*/
}
