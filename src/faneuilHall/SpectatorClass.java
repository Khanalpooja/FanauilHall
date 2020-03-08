/*
Operating System (CS490) Spring 2020, FaneuilHall implementation 
Synchronization Part II, Group 1 
Submitted by : Pooja Khanal, Joshua M Tirone

 */
package faneuilHall;

/**
Spectator thread implementation
**/
public class SpectatorClass implements Runnable {

    private String myName;   // used to distinguish threads
    
    public SpectatorClass(String name) {
        myName = name;
    }
    
    public void enter() throws InterruptedException{
      FeneuilHall.noJudge.acquire();
      System.out.println(myName + "\t has entered");
      FeneuilHall.noJudge.release();
    }
    public void spectate(){
        System.out.println(myName + "\t spectating");
    }
    public void leave(){
        System.out.println(myName + "\t has left");
    }
    
    public void run(){
        try{
        enter();
        spectate();
        leave();
        }
        catch(InterruptedException ex){
        }
        
    }
    
}
