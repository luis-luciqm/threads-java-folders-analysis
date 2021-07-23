/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monitoring;

import java.io.IOException;

public class Myjnotifyapp extends Thread{
    
    public static void main(String[] args) throws IOException {

        ObervaPasta op = new ObervaPasta("#1");
        ThreadLeituraOperacao tlp = new ThreadLeituraOperacao("#2");
    }
}