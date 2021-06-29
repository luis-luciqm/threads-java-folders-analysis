/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoring;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Myjnotifyapp extends Thread{
    
    public static void main(String[] args) throws IOException {

        ObervaPasta op = new ObervaPasta("#1");
//        String pasta1 = op.newDiretorio1;
//        File pasta2 = op.newDiretorio2;
//        op.getFileNameFolder(pasta1, pasta2);

        ThreadLeituraOperacao tlp = new ThreadLeituraOperacao("#2");


    }
}