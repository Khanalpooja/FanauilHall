/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenaulihall;
import java.util.concurrent.*;
/**
 *
 * @author owner
 */
public class FenauliHall {
        public static final Semaphore noJudge = new Semaphore(1); 
        public static final Semaphore mutex = new Semaphore(1);
        public static final Object confirmed = new Object();
        public static final Object allSignedIn = new Object();
        public static int entered = 0;
        public static int checked = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Runnable judge1_run= new JudgeClass("Judge1");
        Thread judge1 = new Thread(judge1_run); //spawning thread one
        judge1.start();
        
        Runnable judge2_run= new JudgeClass("Judge2");
        Thread judge2 = new Thread(judge2_run); //spawning thread one
        judge2.start();
        
        Runnable spectator = new SpectatorClass("Spectator1", 1010);
        Thread spectator1 = new Thread(spectator); //spawning thread one
        spectator1.start();
        
        Runnable immigrants = new ImmigrantsClass("Immigrant1");
        Thread immigrants1 = new Thread(immigrants); //spawning thread one
        immigrants1.start();
        
        
        Runnable immigrants2_run = new ImmigrantsClass("Immigrant2");
        Thread immigrants2 = new Thread(immigrants2_run); //spawning thread one
        immigrants2.start();
        
        Runnable immigrants3_run = new ImmigrantsClass("Immigrant3");
        Thread immigrants3 = new Thread(immigrants3_run); //spawning thread one
        immigrants3.start();
        

    }
}
