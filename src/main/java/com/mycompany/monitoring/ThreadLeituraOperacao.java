/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author luis
 */
public class ThreadLeituraOperacao extends Thread implements GetFile{
    
    private String newDiretorio1 = new File(".").getCanonicalPath()+"/Pasta2/";
    private String newDiretorio2 = new File(".").getCanonicalPath()+"/Pasta3/resultado.txt";
    private String nome;
    
    public ThreadLeituraOperacao(String nome) throws IOException{
        this.nome = nome;
        new Thread(this, nome).start();
    }
 
    @Override
    public void getFileNameFolder(String newDiretorio1, String newDiretorio2) {
        while(true){
            String arquivos = "";

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(newDiretorio1))) {
                for (Path file: stream) {
                    int somatorio = 0;
                    String escrita = "";
                    arquivos = (file + "");
                    
                    String leituraArquivo = lerArquivo(arquivos);
                    
                    try{
                        for(int i = 0; i < leituraArquivo.length(); i++){
                            somatorio += Integer.parseInt(String.valueOf(leituraArquivo.charAt(i)));   
                        }
                        escrita = file.getFileName() + ": " + somatorio;

                        escreve(escrita, newDiretorio2);
                        Files.delete(file);
                    }catch(NumberFormatException e){
                        System.out.println("Não foi possivel analisar o arquivo, verifique se os caracteres são numeros!");
                    }
                    
                    try{
                        Thread.sleep(5000);
                    }catch(InterruptedException excep){
                        System.out.println(excep.getMessage());
                    }
                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }

            if(arquivos.equals("")){
                System.out.println("Nenhum arquivo na pasta2");

            }

            try {
                System.out.println("Pausando a Thread " + this.nome);
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Myjnotifyapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void run(){
        getFileNameFolder(newDiretorio1, newDiretorio2);
    }
    
    public String lerArquivo(String arquivo) throws FileNotFoundException{ // não tem nem como isso continuar sendo void
        String linha = "";
        String arquivoInteiro = "";
        try{
            BufferedReader buffRead = new BufferedReader(new FileReader(arquivo));
            while (true) {
                if (linha != null) {
                    System.out.println(linha);

                } else
                    break;
                linha = buffRead.readLine();
                arquivoInteiro += linha;
                return arquivoInteiro;
            }
            buffRead.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public static void escreve(String valor, String arquivo){
        FileWriter fw = null;
        valor += "\n";
        try{
            fw = new FileWriter(arquivo, true);
            fw.write(valor); // aqui será escrito o arquivo que foi lido anteriormente
            fw.close();
            System.out.println("Salvo com sucesso\n");
        }catch(IOException exe){
            System.out.println("Erro ao escrever no arquivo: " + exe.getMessage());
        }
    }
}
