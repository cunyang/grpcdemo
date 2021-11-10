package com.example.grpcserver;

import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import example.*;

@Service
public class AlignGrpcImpl extends DataAlignGrpc.DataAlignImplBase {

    private static final Logger logger = Logger.getLogger(DataAlignGrpc.DataAlignImplBase.class.getName());

    @Override
    public void getData(DataAlignOuterClass.StringValue request,
                        io.grpc.stub.StreamObserver<DataAlignOuterClass.CsvFile> responseObserver) {
        String filePath = request.getValue();
        responseObserver.onNext(getFileFromDisk(filePath));
        responseObserver.onCompleted();
    }

    private DataAlignOuterClass.CsvFile getFileFromDisk(String filePath) {
        DataAlignOuterClass.CsvFile.Builder csvBuilder = DataAlignOuterClass.CsvFile.newBuilder();
        Path path = Paths.get(filePath);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvBuilder.setData(ByteString.copyFrom(data));
        csvBuilder.setName(path.getFileName().toString());
        return csvBuilder.build();
    }

}
