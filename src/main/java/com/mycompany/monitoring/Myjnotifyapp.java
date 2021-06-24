/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoring;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Myjnotifyapp{
    
    public static void main(String[] args) throws IOException {
        String arquivos = getFileNameFolder();
        
        if(arquivos.equals("")){
            System.out.println("Nenhum arquivo encontrado!");
        }else{
            System.out.println("Arquivos:\n-------\n " + arquivos);
        }
                
    }
    
    public static String getFileNameFolder(){
        String arquivos = "";
        int cont = 0;
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("/home/luis/Documentos/NovaPasta"))) {
            for (Path file: stream) {
                arquivos += (file.getFileName() + ";");
                cont++;   
            }
            
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
        if(cont == 0)
            return "";
        return arquivos;
    }
}