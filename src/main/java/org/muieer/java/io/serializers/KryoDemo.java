package org.muieer.java.io.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class KryoDemo {
    public static void main(String[] args) throws Exception {

        Kryo kryo = new Kryo();
        kryo.register(ArrayList.class);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("test1");

        System.out.printf("serialize list1: %s\n", list1);
        Output output1 = new Output(new FileOutputStream("file.bin1"));
        kryo.writeObject(output1, list1);
        output1.close();

        Input input1 = new Input(new FileInputStream("file.bin1"));
        ArrayList object1 = kryo.readObject(input1, ArrayList.class);
        input1.close();
        System.out.printf("deserialize object1: %s\n", object1);

// ------------ 分割线 ---------------

        ArrayList<String> list2 = new ArrayList<String>(){{add("test2");}};

        System.out.printf("serialize list2: %s\n", list2);
        Output output2 = new Output(new FileOutputStream("file.bin2"));
        kryo.writeObject(output2, list2);
        output2.close();

        Input input2 = new Input(new FileInputStream("file.bin2"));
        ArrayList object2 = kryo.readObject(input2, ArrayList.class);
        input2.close();
        System.out.printf("deserialize object2: %s\n", object2);
    }
}