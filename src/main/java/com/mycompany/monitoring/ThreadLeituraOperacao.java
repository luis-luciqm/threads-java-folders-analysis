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
    
    private String nome;
    public ThreadLeituraOperacao(String nome) throws IOException{
        this.nome = nome;
        new Thread(this, nome).start();
    }
    
    
    String newDiretorio1 = new File(".").getCanonicalPath()+"/Pasta2/";
    String newDiretorio2 = new File(".").getCanonicalPath()+"/Pasta3/resultado.txt";
 
    @Override
    public void getFileNameFolder(String newDiretorio1, String newDiretorio2) {
        while(true){
            String arquivos = "";

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(newDiretorio1))) {
                for (Path file: stream) {
                    int somatorio = 0;
                    String escrita = "";
                    arquivos = (file + "");

//                    pegar o arquivo de algum jeito
//                    System.out.println(arquivos);
                    String leituraArquivo = lerArquivo(arquivos);
                    
                    for(int i = 0; i < leituraArquivo.length(); i++){
                        somatorio += Integer.parseInt(String.valueOf(leituraArquivo.charAt(i)));
                        
                    }
                    System.out.println(somatorio);
                    escrita = file.getFileName() + ": " + somatorio;
                    System.out.println(escrita);
                    
                    System.out.println(newDiretorio2);
                    escreve(escrita, newDiretorio2);
                    
                    

//                    ler o arquivo
//                    obter os dados
//                    fazer a soma
//                    salvar o nome do arquivo e o resultado em um arquivo.txt em pasta3
//                    deletar o arquivo da pasta2

                    
//                    System.out.println("\nMovendo " + file.getFileName());
                    
                    

//                    moveArquivo(newDiretorio1 + file.getFileName(), newDiretorio2);
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
    
    //    inicio função escrever1()
    public static void escreve(String valor, String arquivo){
        FileWriter fw = null;
        try{
            fw = new FileWriter(arquivo);
            fw.write(valor); // aqui será escrito o arquivo que foi lido anteriormente
            fw.close();
            System.out.println("Salvo com sucesso");
        }catch(IOException exe){
            System.out.println("Erro ao escrever no arquivo: " + exe.getMessage());
        }
    }

//    @Override
//    public void getFileNameFolder(String newDiretorio1, File newDiretorio2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
}
