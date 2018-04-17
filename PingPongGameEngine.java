/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import PingPongGreenTable.PingPongGreenTable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import screens.*;

/**
 *
 * @author as
 */
public class PingPongGameEngine implements Runnable, MouseMotionListener, KeyListener, GameConstants{
  
private final PingPongGreenTable table;
private int kidRacket_Y = KID_RACKET_Y_START;
private int computerRacket_Y=COMPUTER_RACKET_Y_START;	
private int kidScore;
private int computerScore;
private int ballX;
private int ballY;
private boolean movingLeft = true;	
private boolean ballServed = false;
private int verticalSlide;
    
public PingPongGameEngine(PingPongGreenTable greenTable) {	
  
    table = greenTable;
    Thread worker = new Thread(this);
    worker.start();
    
}

    /**
     *
     * @param e
     */
    @Override
public void mouseDragged(MouseEvent e) {
	
}

    /**
     *
     * @param e
     */
    @Override
public void mouseMoved(MouseEvent e) {
	
	int mouse_Y = e.getY();
	
	  if (mouse_Y<kidRacket_Y && kidRacket_Y>TABLE_TOP){
		  
		  kidRacket_Y -= RACKET_INCREMENT;
		  
	  }else if (kidRacket_Y < TABLE_BOTTOM) {
		  
		  kidRacket_Y += RACKET_INCREMENT;
		  
}

      table.setKidRacket_Y(kidRacket_Y);
      
}

@Override
public void keyPressed(KeyEvent e){
	
	char key = e.getKeyChar();
	
    switch (key) {
        case 'n':
        case 'N':
            startNewGame();
            break;
        case 'q':
        case 'Q':
            endGame();
            break;
        case 's':
        case 'S':
            kidServe();
            break;
        default:
            break;
    }
}

    /**
     *
     * @param e
     */
    @Override
public void keyReleased(KeyEvent e){}

    /**
     *
     * @param e
     */
    @Override
public void keyTyped(KeyEvent e){}

public void startNewGame(){
	
	computerScore=0;
    kidScore=0; 
    table.setMessageText("Score Computer: 0 Kid: 0");
    
    kidServe();
}

public void endGame(){	
  
  System.exit(0);
  
}

    /**
     *
     */
    @Override
public void run(){
	
	boolean canBounce=false;
	
	while (true) {
		
		if(ballServed){
			
		if ( movingLeft && ballX > BALL_MIN_X){
			
			canBounce = (ballY >= computerRacket_Y &&
                                ballY < (computerRacket_Y + RACKET_LENGTH));
			
			ballX-=BALL_INCREMENT;
			
			ballY-=verticalSlide;
			
			table.setBallPosition(ballX,ballY);
                    boolean canBouce = false;
                    
			
			if (ballX <= COMPUTER_RACKET_X && canBouce){
				
				movingLeft=false;
  }
}

if (!movingLeft && ballX <= BALL_MAX_X){ 
	
	canBounce = (ballY >= kidRacket_Y && ballY <
                (kidRacket_Y + RACKET_LENGTH));
	    
	ballX+=BALL_INCREMENT;
    table.setBallPosition(ballX,ballY); 
    
    if (ballX >= KID_RACKET_X && canBounce){
		movingLeft=true;
  }
}

  	if (computerRacket_Y < ballY
  	          && computerRacket_Y < TABLE_BOTTOM){
				  
				  computerRacket_Y +=RACKET_INCREMENT;
				  
	}else if (computerRacket_Y > TABLE_TOP){
		
		computerRacket_Y -=RACKET_INCREMENT;
  }
  
  table.setComputerRacket_Y(computerRacket_Y);
  
try {
	
	Thread.sleep(SLEEP_TIME);
	
} catch (InterruptedException e) {
}

  if (isBallOnTheTable()){
	  
	  if (ballX > BALL_MAX_X ){
		  
		  computerScore++;
          displayScore();	
          
  }else if (ballX < BALL_MIN_X){
	  
	  kidScore++;
      displayScore();   
      
     }
    }
   }
 }
}

private void kidServe(){
	
	ballServed = true;
    ballX = KID_RACKET_X-1;
    ballY=kidRacket_Y; 
    
    if (ballY > TABLE_HEIGHT/2){
		
		verticalSlide=-1;
		
	}else{
		
		verticalSlide=1;
	}
	
	table.setBallPosition(ballX,ballY);
    table.setKidRacket_Y(kidRacket_Y);  
  }
  
private void displayScore(){
	
	ballServed = false;
	
	if (computerScore ==WINNING_SCORE){
		
		table.setMessageText("Computer won! " + computerScore + ":" + kidScore);  
                                              
    }else if (kidScore ==WINNING_SCORE){
		
		table.setMessageText("You won! "+ kidScore + ":" + computerScore);                                           		  
			
	}else{
		
		table.setMessageText("Computer: "+ computerScore + " Kid: " + kidScore);		
   }
}

private boolean isBallOnTheTable(){
	
    return ballY >= BALL_MIN_Y && ballY <= BALL_MAX_Y;
  }
}	
		  
