package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream


    fun main(){
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
        println(request)

        request.writeTo(FileOutputStream("funcionario request.bin")) // serializando obj

        val request2 = Funcionario.newBuilder().mergeFrom(FileInputStream("funcionario request.bin"))
    }
