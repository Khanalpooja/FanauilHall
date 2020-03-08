/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenaulihall;

/**
 *
 * @author owner
 */
public class SpectatorClass implements Runnable {
    
    
    
    private int myCounter;   // each thread will have its own message counter
    private String myName;   // used to distinguish threads
    private int myDelay; 
    
    public SpectatorClass(String name, int delay) {
        
        myCounter = 0; 
        myName = name;
        myDelay= delay;
    }
    
    public void enter(String myName) throws InterruptedException{
      FenauliHall.noJudge.acquire();
      System.out.println(myName + "\t has enetered");
      FenauliHall.noJudge.release();
    }
    public void spectate(String myName){
        System.out.println(myName + "\t spectating");
    }
    public void leave(String myName){
        System.out.println(myName + "\t has left");
    }
    
    public void run(){
        try{
        enter(myName);
        spectate(myName);
        leave(myName);
        }
        catch(InterruptedException ex){
        }
        
    }
    
}
