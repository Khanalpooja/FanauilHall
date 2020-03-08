/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenaulihall;
import java.util.Random;

/**
 *
 * @author owner
 */
public class ImmigrantsClass implements Runnable{
    
    
    
    private String myName;   // used to distinguish threads
 
    
    public ImmigrantsClass(String name) {
       
        myName = name;
  
    }
 
    public void enter() throws InterruptedException{
      FenauliHall.noJudge.acquire();
      FenauliHall.mutex.acquire(); // mutex bhaneko chai one by one, count garne jasto ko lagi 
      System.out.println(myName + "\t has enetered");
      FenauliHall.entered++ ;
      FenauliHall.mutex.release(); 
      FenauliHall.noJudge.release();
    }
    public void checkIn()throws InterruptedException{
      FenauliHall.mutex.acquire(); // mutex bhaneko chai one by one, count garne jasto ko lagi 
      System.out.println(myName + "\t has checked in.");
      FenauliHall.checked++ ;
      if (FenauliHall.entered == FenauliHall.checked){
          synchronized(FenauliHall.allSignedIn) {
              FenauliHall.allSignedIn.notify();
          }
      }
      FenauliHall.mutex.release(); 
    }
    
    
    
    public void sitDown() throws InterruptedException{
        System.out.println(myName + "\t sat down");
    }
    
    public void swear()throws InterruptedException{
        System.out.println(myName + "\t has sweared");    
    }
    
    public void getCertificate() throws InterruptedException{
        synchronized(FenauliHall.confirmed) {
            FenauliHall.confirmed.wait();
        }
        System.out.println(myName + "\t got the certificate");
    }
    
    public void leave() throws InterruptedException{
        //la bye bye taatat
      FenauliHall.noJudge.acquire();
      System.out.println(myName+ "\t has left");
      FenauliHall.noJudge.release();
    }
    public void run() {
        try{
        Random rand = new Random();
        Thread.sleep(rand.nextInt(1000));
        enter();
        Thread.sleep(rand.nextInt(1000));
        checkIn();
//        sitDown();
        swear();
        
        getCertificate();
        Thread.sleep(rand.nextInt(1000));
        leave();
        }
        catch(InterruptedException ex){
        
        }
    }
    
}
