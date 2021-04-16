package cn.ljpc.framework.register;

import cn.ljpc.framework.URL;

import java.io.*;
import java.util.*;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 10:26
 * 注册中心
 */
public class RemoteRegister {

    private static Map<String, List<URL>> map = new HashMap<>();

    private static String path = "D:\\CodeSource\\JavaCode\\pure java\\idea\\netty4-samples\\micservicerpc\\map";

    public static void register(String interfaceName, URL url) {
        List<URL> urls = map.get(interfaceName);
        if (urls == null) {
            urls = new LinkedList<>();
        }
        urls.add(url);

        map.put(interfaceName, urls);
        saveFile();
    }

    public static List<URL> get(Class interfaceName) {
        readFile();
        //返回一个不可修改的list
        return Collections.unmodifiableList(map.get(interfaceName.getName()));
    }

    private static void saveFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            map = (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
