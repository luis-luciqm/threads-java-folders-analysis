package com.mycompany.monitoring;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class ObervaPasta extends Thread{
    public static void getFileNameFolder(){
        while(true){
            String arquivos = "";
            int cont = 0;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("/home/luis/NetBeansProjects/Monitoring/Pasta1"))) {
                for (Path file: stream) {
                    arquivos += (file.getFileName());
                    cont++;
                }

            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }

            if(arquivos.equals("")){
                System.out.println("Nada");
            }else{
                System.out.println(arquivos); // pq esse omh  n enviou nenhum arquivo, pere ai, pronto testar agr
                moveArquivo("/home/luis/NetBeansProjects/Monitoring/Pasta1/" + arquivos); //pasta2 ou 1 ?f
            }
            /* perai, antes bora ver se ele move, como escolho onde ele vai mover?
            *
            *
            *
            *
            * ---------THREAD1---------------------
            * 1 VERIFICAR SE TER ARQUIVOS NA PASTA 1
            * 2 MOVER OS ARQUIVOS PARA A PASTA 2
            * ------------THREAD 2------------------
            * 3 SOMAR OS DADOS DO ARQUIVO ex: 1234 = 10
            * 4 ESCREVER OS RESULTADOS DAS SOMAS NUM ARQUIVO NA PASTA 3
            * 5 NA PASTA 3 TEM Q VERIFICAR SE EXISTE UM ARQUIVO CHAMADO final.txt SE NAO EXISTIR TEM Q CRIAR
            * 6 O ARQUIVO final.txt DEVE SALVAR OS RESULTADOS DA SEGUINTE FORMA (arq1.txt: 45) (arq2.txt : 120)
            * */
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Myjnotifyapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
                                                //
    public static void moveArquivo(String nome){ //
        // Arquivo a ser movido, tem que ser static msm
        File arquivo = new File(nome);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado");
        } else {

            // Diretorio de destino
            File diretorioDestino = new File("/home/luis/NetBeansProjects/Monitoring/Pasta2");

            // Move o arquivo para o novo diretorio
            boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
            if (sucesso) {
                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
            } else {
                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
                        + diretorioDestino.getAbsolutePath() + "'");
            }
        }
    }
}

