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
        
        
        Runnable spectator1_run = new SpectatorClass("Spectator1");
        Thread spectator1 = new Thread(spectator1_run); //spawning thread one
        spectator1.start();
        
        Runnable immigrants1_run = new ImmigrantsClass("Immigrant1");
        Thread immigrants1 = new Thread(immigrants1_run); //spawning thread one
        immigrants1.start();
        
        
        Runnable immigrants2_run = new ImmigrantsClass("Immigrant2");
        Thread immigrants2 = new Thread(immigrants2_run); //spawning thread one
        immigrants2.start();
        
        Runnable immigrants3_run = new ImmigrantsClass("Immigrant3");
        Thread immigrants3 = new Thread(immigrants3_run); //spawning thread one
        immigrants3.start();
        
        
        Runnable immigrants4_run = new ImmigrantsClass("Immigrant4");
        Thread immigrants4 = new Thread(immigrants4_run); //spawning thread one
        immigrants4.start();
        
        
        Runnable immigrants5_run = new ImmigrantsClass("Immigrant5");
        Thread immigrants5 = new Thread(immigrants5_run); //spawning thread one
        immigrants5.start();
        
        Runnable immigrants6_run = new ImmigrantsClass("Immigrant6");
        Thread immigrants6 = new Thread(immigrants6_run); //spawning thread one
        immigrants6.start();
        
        Runnable spectator2_run = new SpectatorClass("Spectator2");
        Thread spectator2 = new Thread(spectator2_run); //spawning thread one
        spectator2.start();

        Runnable judge1_run= new JudgeClass("Judge1");
        Thread judge1 = new Thread(judge1_run); //spawning thread one
        judge1.start();
        
        Runnable judge2_run= new JudgeClass("Judge2");
        Thread judge2 = new Thread(judge2_run); //spawning thread one
        judge2.start();
        
    }
}
