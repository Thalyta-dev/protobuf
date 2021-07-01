package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main(){
    val channel = ManagedChannelBuilder.forAddress("localhost", 8082).usePlaintext().build()


    val request = Funcionario.newBuilder()
        .setNome("Yuri")
        .setCargo(Cargo.DEV)
        .setCpf("111111111")
        .setAtivo(true)
        .setSalario(2000.0)
        .addEnderecos(Endereco.newBuilder()
            .setCep("38970000")
            .setLagradouro("travessa vicente de paula pedroso")
            .setComplemento("perto da ifgrja da fe").build()

        ).build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    val cadastrar = client.cadastrar(request)

    println(cadastrar)
}