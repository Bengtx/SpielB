import sas.*;
import sasio.*;
import java.awt.Color;

public class Spieler  {
  
  // Anfang Attribute
  int hitTimer = 0;
  Rectangle koerper;
  int anzahlSchuesse = 100;
  Rectangle lifebar;
  
  
  Rectangle[] schuesse = new Rectangle[anzahlSchuesse]  ;
  
  private int lifes = 20;       
  static int MAXSHOT = 8;
  static int SHOTDURATION = 60;
  int shotCounter = MAXSHOT;
  int nummer;

  double lifebarX;
  double lifebarY;

  //Ende Attribute
  
  // Anfang Methoden
  public Rectangle getKoerper() {
    return koerper;
  }
  
  public Spieler(int n,double x, double y,double lifebarX, double lifebarY){
    nummer = n;  
    koerper= new Rectangle(x,y,20,20);
    this.lifebarX = lifebarX;
    this.lifebarY = lifebarY;
    lifebar =new Rectangle (lifebarX,lifebarY,getLifes()*10,10) ;
  } 
  public int getNummer() {
    return nummer;
  } 

  public void handleHit() {
    if (koerper.getColor() == Color.red) {
      hitTimer++;
      if (hitTimer == 50) {
        koerper.setColor(Color.black) ;
        hitTimer = 0;
      }
    }
  }

  public void handleCollision (Spieler otherSpieler) {
    for (int i = 0;i < anzahlSchuesse ;i++ ) {
      if(schuesse[i] != null){
        schuesse[i].move(6) ;
        if (schuesse[i].intersects(otherSpieler.koerper)) {
          otherSpieler.koerper.setColor(Color.red);
          otherSpieler.loseLife();
          otherSpieler.lifebar.setHidden(true);
          otherSpieler.lifebar = new Rectangle(otherSpieler.lifebarX,otherSpieler.lifebarY,otherSpieler.getLifes()*10,10);
          schuesse[i].setHidden(true);
          schuesse[i] = null;
        }else if (schuesse[i].getCenterX()<0 ||schuesse[i].getCenterX()>1000 || schuesse[i].getCenterY()<0 ||schuesse[i].getCenterY()>1000) {
          schuesse[i].setHidden(true);
          schuesse[i] = null;
        }
      }
    } 
    
    // nachfragen
    if (shotCounter == 0) {
      shotCounter = SHOTDURATION; 
    } 
    if (shotCounter > MAXSHOT) {
      shotCounter-- ;
    } 
    
  }
  public void loseLife() {
    lifes--;
  }       

  private int getFreeShot() {
    for (int i = 0; i < schuesse.length ;i ++ ) {
      if (schuesse[i] == null) {
        return i;
      } // end of if
    } // end of for
    return schuesse.length;
  }
  
  public void rechts() {
    koerper.setDirection(90);
    koerper.move(5);
  }
  public void links() {
    koerper.setDirection(270);
    koerper.move(5);
  }
  public void hoch(){
    koerper.setDirection(0);
    koerper.move(5);
  }
  public void runter(){
    koerper.setDirection(180);
    koerper.move(5);
  } 
  public int getLifes() {
    return lifes;
  }
  public void fire() {
    if (shotCounter > 0 && shotCounter <= MAXSHOT) {
      shotCounter--;
      for (int i =0; i < 4 ; i++ ) {
        int f = getFreeShot() ;
        if (f != anzahlSchuesse) {
          if (i == 0) {  
            schuesse[f]= new Rectangle(koerper.getCenterX()  ,koerper.getCenterY() -10,1,1);
          } // end of if
          if (i == 1) {  
            schuesse[f]= new Rectangle(koerper.getCenterX() + 10,koerper.getCenterY()  ,1,1);
          } // end of if
          if (i == 2) {  
            schuesse[f]= new Rectangle(koerper.getCenterX()  ,koerper.getCenterY() +10,1,1);
          } // end of if
          if (i == 3) {  
            schuesse[f]= new Rectangle(koerper.getCenterX() -10,koerper.getCenterY()  ,1,1);
          } // end of if
          schuesse[f].setDirection(i * 90);
        }
      } 
    }
  }    
}