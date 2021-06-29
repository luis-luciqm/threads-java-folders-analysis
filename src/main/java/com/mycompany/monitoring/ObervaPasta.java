package com.mycompany.monitoring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static java.lang.Thread.sleep;

public class ObervaPasta extends Thread{
    
    private String nome;
    public ObervaPasta(String nome) throws IOException{
        this.nome = nome;
        new Thread(this, nome).start();
    }
    
    String newDiretorio1 = new File(".").getCanonicalPath()+"/Pasta1/";
    File newDiretorio2;
    {
        try {
            newDiretorio2 = new File(new File(".").getCanonicalPath() + "/Pasta2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        getFileNameFolder(newDiretorio1, newDiretorio2);
    }

    public void getFileNameFolder(String newDiretorio1, File newDiretorio2){
        while(true){
            String arquivos = "";

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(newDiretorio1))) {
                for (Path file: stream) {
                    arquivos += (file.getFileName() + ";");

                    System.out.println("\nMovendo " + file.getFileName());

                    moveArquivo(newDiretorio1 + file.getFileName(), newDiretorio2);
//                    System.out.println(file);

//                    file.getFileName() exibe apenas o arquivo
//                    file exibe o caminho + o arquivo.txt

                    /*

                    cada iteração, file é APENAS UM ARQUIVO
                    logica: pegar cada arquivo de uma vez só: -OK-
                    esperar x segundos para administrar esse arquivo, uns 10 segundos: -OK-

                    pegando o arquivo, devemos move-lo para a pasta 2: OK

                    fazer a leitura e operação matematica

                    */

//                    System.out.println(arquivos);

                    try{
                        System.out.println("Pausando a Thread " + this.nome);
                        Thread.sleep(4000);
                    }catch(InterruptedException excep){
                        System.out.println(excep.getMessage());
                    }

                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }

            if(arquivos.equals("")){
                System.out.println("Nenhum arquivo na pasta1");

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
                System.out.println("Pausando a Thread " + this.nome);
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Myjnotifyapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void moveArquivo(String nome, File destino){
        File arquivo = new File(nome);

        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado");
        } else {
            boolean sucesso = arquivo.renameTo(new File(destino, arquivo.getName()));
            if (sucesso) {
                System.out.println("Arquivo movido para '" + destino.getAbsolutePath() + "'");
            } else {
                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
                        + destino.getAbsolutePath() + "'");
            }
        }
    }
}

