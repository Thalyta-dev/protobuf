package br.com.zup

import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


fun main(){
        val server = ServerBuilder.forPort(8082).addService(Endpoint()).build()

        server.start()
        server.awaitTermination()
    }


class Endpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase(){
    override fun cadastrar(request: Funcionario?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        var nome: String? = request?.nome

        if (!request?.hasField(Funcionario.getDescriptor().findFieldByName("nome"))!!) {

            nome = "[???]"

        }

        val data = LocalDateTime.now().atZone(
            ZoneId.of("UTC")).toInstant()

        val criadoEm = com.google.protobuf.Timestamp.newBuilder().setSeconds(data.epochSecond).setNanos(data.nano).build()
        val  response = FuncionarioResponse.newBuilder()
            .setDate(criadoEm).setNome(nome).build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}