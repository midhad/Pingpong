/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PingPongGreenTable;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import engine.PingPongGameEngine;
import java.awt.event.MouseListener;
import static screens.GameConstants.BALL_START_X;
import static screens.GameConstants.BALL_START_Y;
import static screens.GameConstants.COMPUTER_RACKET_Y_START;
import static screens.GameConstants.KID_RACKET_X;
import static screens.GameConstants.KID_RACKET_Y_START;
import static screens.GameConstants.RACKET_LENGTH;
import static screens.GameConstants.TABLE_HEIGHT;
import static screens.GameConstants.TABLE_WIDTH;

public class PingPongGreenTable extends JPanel implements GameConstants{
    
  private JLabel label;
  private int computerRacket_Y = COMPUTER_RACKET_Y_START;
  private int kidRacket_Y = KID_RACKET_Y_START;
  private int ballX = BALL_START_X;
  private int ballY = BALL_START_Y;

  Dimension preferredSize= new Dimension(TABLE_WIDTH,TABLE_HEIGHT);
  
    
    /**
     *
     * @return
     */
    @Override
  public Dimension getPreferredSize() {
        
    return preferredSize;
 }
    
    PingPongGreenTable(){
        
        PingPongGameEngine gameEngine = new PingPongGameEngine(this);
        
        addMouseListener((MouseListener) gameEngine);
        
        addKeyListener(gameEngine);
        
    }
    
    void addPaneltoFrame(Container container) {
        
        container.setLayout(new BoxLayout(container,
                                        BoxLayout.Y_AXIS));
        
        container.add(this);
        
        label = new JLabel("Press N for a new game, S to serve or Q to quit");
        
        container.add(label);
    }
    
    /**
     *
     * @param g
     */
    @Override
  public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.setColor(Color.GREEN);        
        g.fillRect(0,0,TABLE_WIDTH,TABLE_HEIGHT);
        g.setColor(Color.yellow);      
      int RACKET_WIDTH = 0;
        g.fillRect(KID_RACKET_X, kidRacket_Y, RACKET_WIDTH, RACKET_LENGTH);
        g.setColor(Color.blue);
      int COMPUTER_RACKET_X = 0;
      
        g.fillRect(COMPUTER_RACKET_X, computerRacket_Y, RACKET_WIDTH,RACKET_LENGTH);
        g.setColor(Color.red);      
        g.fillOval(ballX,ballY,10,10);
        g.setColor(Color.white);
        g.drawRect(10,10,300,200);
        g.drawLine(160,10,160,210);
        
        requestFocus();
      
    }
    
public void setKidRacket_Y(int xCoordinate){
      int yCoordinate = 0;
	
    this.kidRacket_Y = yCoordinate;
    repaint();
    
}
    
    public int getKidRacket_Y(){
        
        return kidRacket_Y;
}

public void setComputerRacket_Y(int yCoordinate){
	
	this.computerRacket_Y = yCoordinate;
	repaint();
}

public void setMessageText(String text){
	
	label.setText(text);
	repaint();
}
	
   public void setBallPosition(int xPos, int yPos){	
	   
	   ballX=xPos;
       ballY=yPos;
       repaint();
}
       
    public static void main(String[] args) {
        
        JFrame f = new JFrame("Ping Pong Green Table");
        
          f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          
          PingPongGreenTable table = new PingPongGreenTable();
          
          table.addPaneltoFrame(f.getContentPane());
          
          f.setBounds(0,0,TABLE_WIDTH+5, TABLE_HEIGHT+40);
          f.setVisible(true);
    }    
}
