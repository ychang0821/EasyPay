package com.easypay.client;

import com.apps.util.SplashApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main implements SplashApp {
    @Override
    public void start() {
        try{
            File myObj = new File("banner.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch(FileNotFoundException e){
            System.out.println("An error occurred. ");
            e.printStackTrace();
        }
        System.out.println("\nW E L C O M E   T O   T H E   E A S Y P A Y");
        System.out.println( "by ACCESS DENIED\n\n");

        Controller controller = new Controller();
        controller.initialize();
    }
    public static void main(String[] args) {
        Main app = new Main();
        app.welcome("images/debt.png");
        app.start();
    }
}
