package com.example.grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class GrpcInitialization {

    private static final Logger logger = Logger.getLogger(GrpcInitialization.class.getName());

    private Server server;

    public void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new AlignGrpcImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
