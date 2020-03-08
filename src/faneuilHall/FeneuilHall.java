/*
Operating System (CS490) Spring 2020, FaneuilHall implementation 
Synchronization Part II, Group 1 
Submitted by : Pooja Khanal, Joshua M Tirone

 */
package faneuilHall;
import java.util.concurrent.*;

//main class 

public class FeneuilHall {
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
        
        int noOfImmigrants = 100; // number of immigrants
        int noOfJudges = 2; // number of judges
        int noOfSpectators = 100 ; // number of spectators

        for (int k =1; k<= noOfSpectators; k++){
            Runnable spectator = new SpectatorClass("Spectator"+k);
            Thread spectatorThread = new Thread(spectator);  // spawn spectator threads
            spectatorThread.start();
        }
        
        for (int k =1; k<= noOfImmigrants; k++){
            Runnable immigrant= new ImmigrantsClass("Immigrant"+k);
            Thread immigrantsThread = new Thread(immigrant);  // spawn immigrant thread
            immigrantsThread.start();
        }
        
        for (int k =1; k<= noOfJudges; k++){
            Runnable judge= new JudgeClass("Judge"+k);
            Thread judgeThread = new Thread(judge);  // spawn judge thread 
            judgeThread.start();
        }
 
    }
}
