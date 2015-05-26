/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;
//package concurrent;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

class Semcolln{
    static Semaphore Reader = new Semaphore(5);
    static Semaphore Writer = new Semaphore(1);
}
class Writer implements Runnable{
    @Override
    public void run(){
        
        while(true){
            if(Semcolln.Reader.availablePermits()==5){
                
                try {
                    Semcolln.Reader.acquire(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i = 0; i<10;i++){
                    System.out.println("Writing" + Semcolln.Reader.availablePermits());
                }
                Semcolln.Reader.release(5);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
class Reader implements Runnable{
    int id;
    Reader(int i){
        id = i;
    }
    public void run(){
        while(true){
            if(Semcolln.Reader.availablePermits() > 0){
                try {
                    Semcolln.Reader.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(int i = 0; i<3;i++){
                    System.out.println("Reading" + id);
                }
                Semcolln.Reader.release();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
}
/**
 *
 * @author tesla pc
 */
public class readerwriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i =0; i<5 ; i++){
            new Thread(new Writer()).start();
        }
    }
    
}
