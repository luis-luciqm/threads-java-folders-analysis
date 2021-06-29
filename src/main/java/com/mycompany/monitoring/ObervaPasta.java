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
    File newDiretorio2 = new File(new File(".").getCanonicalPath() + "/Pasta2");
    
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
                    
                    try{
                        moveArquivo(newDiretorio1 + file.getFileName(), newDiretorio2);
                    }catch(NullPointerException e){
                        System.out.println("Não foi possivel mover para pasta2!");
                    }

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

