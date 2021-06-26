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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Myjnotifyapp extends Thread{
    private String nome;
    public Myjnotifyapp(String nome){
        this.nome  = nome;
        new Thread(this, nome).start();
    }
    
    public static void main(String[] args) throws IOException {

        ObervaPasta op = new ObervaPasta();
        String pasta1 = op.newDiretorio;
        op.getFileNameFolder(pasta1);

    }
}