import sas.*;
import sasio.*;

import java.awt.Button;
import java.awt.Color;



//Hintergrund
//Hauptmenue mit Animation
//Attacken



public class Spiel {


  public static void main(String[] args) {

    View fenster = new View(1000, 1000, "Spiel");
    
    /*Picture start = new Picture(0,0,1000,1000,"Sartscreen.png");
    Button press = new Button(450,825,100,50,"PLAY",Color.orange);

    press.setActivated(true);
    
    while (true) {

      if (press.clicked()) {

        System.out.println(press.clicked());
        start.setHidden(true);
        
        press.setHidden(true);
        
        
        break;  
      } // end of if
    } // end of while
*/
    Picture back = new Picture(0, 0, 1000, 1000, "Background.png");
    Spieler spieler1 = new Spieler(1, 100, 100, 30,30);
    Spieler spieler2 = new Spieler(2, 900, 900, 770,30);
    Spieler winner = null;

    while (winner == null) {
      spieler1.handleHit();
      spieler1.handleCollision(spieler2);
      spieler2.handleHit();
      spieler2.handleCollision(spieler1);

      if (spieler1.getLifes() <= 0) {
        winner = spieler2;
        Picture gameover1 = new Picture(340, 340, 300, 300, "player 2 win.png");
      }
      if (spieler2.getLifes() <= 0) {
        winner = spieler1;
        Picture gameover2 = new Picture(340, 340, 300, 300, "player 1 win.png");
      }

      //Button restart = new Button(450,825,100,50,"RESTART",Color.orange);
      //if (restart.clicked())
        fenster.wait(10);
      // Spieler 1
      if (fenster.keyPressed('d') && spieler1.getKoerper().getShapeX() < 980) {
        spieler1.rechts();
      }
      if (fenster.keyPressed('a') && spieler1.getKoerper().getShapeX() > 0) {
        spieler1.links();
      }
      if (fenster.keyPressed('w') && spieler1.getKoerper().getShapeY() > 0) {
        spieler1.hoch();
      }
      if (fenster.keyPressed('s') && spieler1.getKoerper().getShapeY() < 980) {
        spieler1.runter();
      }
      if (fenster.keyPressed(' ')) {
        spieler1.fire();
      }


      // Spieler 2

      if (fenster.keyRightPressed() && spieler2.getKoerper().getShapeX() < 980) {
        spieler2.rechts();
      }
      if (fenster.keyLeftPressed() && spieler2.getKoerper().getShapeX() > 0) {
        spieler2.links();
      }
      if (fenster.keyUpPressed() && spieler2.getKoerper().getShapeY() > 0) {
        spieler2.hoch();
      }
      if (fenster.keyDownPressed() && spieler2.getKoerper().getShapeY() < 980) {
        spieler2.runter();
      }
      if (fenster.keyPressed('l')) {
        spieler2.fire();
      }
    }
  }
}




