package org.muieer.java.file_format.proto;

import com.google.protobuf.ByteString;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.tensorflow.example.*;

import java.util.List;

// https://www.tensorflow.org/tutorials/load_data/tfrecord
public class TensorflowTfrecordDemo {

    public static void main(String[] args) throws Exception {

        writeExampleToFile(buildExample(), args);
        readExampleFromFile(args);
    }

    public static void readExampleFromFile(String[] args) throws Exception {

        try (
                FileSystem fs = FileSystem.get(new Configuration());
                FSDataInputStream inputStream = fs.open(new Path(args[0]))
        ) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            Example example = Example.parseFrom(bytes);
            System.out.println(example);
        }
    }

    public static void writeExampleToFile(Example example, String[] args) throws Exception {

        try (
                FileSystem fs = FileSystem.get(new Configuration());
                FSDataOutputStream outputStream = fs.create(new Path(args[0]))
        ) {
            System.out.println(example);
            IOUtils.write(example.toByteArray(), outputStream);
        }
    }

    public static Example buildExample() {

        Example.Builder exampleBuilder = Example.newBuilder();
        Features.Builder featuresBuilder = Features.newBuilder();

        Int64List int64List = Int64List.newBuilder().addAllValue(List.of(1L, 2L)).build();
        Feature longFeature = Feature.newBuilder().setInt64List(int64List).build();
        featuresBuilder.putFeature("cityId", longFeature);

        FloatList floatList = FloatList.newBuilder().addAllValue(List.of(0.1F, 0.2F)).build();
        Feature floatFeature = Feature.newBuilder().setFloatList(floatList).build();
        featuresBuilder.putFeature("cvr", floatFeature);

        FloatList floatList1 = FloatList.newBuilder().build();
        Feature floatFeature1 = Feature.newBuilder().setFloatList(floatList1).build();
        featuresBuilder.putFeature("ctr", floatFeature1);

        BytesList bytesList = BytesList.newBuilder().addAllValue(List.of(ByteString.copyFromUtf8("test"))).build();
        Feature bytesFeature = Feature.newBuilder().setBytesList(bytesList).build();
        featuresBuilder.putFeature("extraData", bytesFeature);

        Example example = exampleBuilder.setFeatures(featuresBuilder.build()).build();
        return example;
    }

}
