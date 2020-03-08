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

    private String myName;   // used to distinguish threads

    
    public SpectatorClass(String name) {

        myName = name;

    }
    
    public void enter() throws InterruptedException{
      FenauliHall.noJudge.acquire();
      System.out.println(myName + "\t has entered");
      FenauliHall.noJudge.release();
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
