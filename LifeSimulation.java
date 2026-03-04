/**
* Author: Chididebere Okafor 
* 
* Purpose of the class: A basic implementation of an ArrayList.
* that provides basic operations
* for adding and removing elements at the end of the ArrayList
* as well as getting and setting elements at a specified index.
*
*/
public class LifeSimulation{

    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(150, 150, .25);

        LandscapeDisplay display = new LandscapeDisplay(scape, 6);

        while (true) {
        Thread.sleep(250);
        scape.advance();
        display.repaint();
        }

    }
}

