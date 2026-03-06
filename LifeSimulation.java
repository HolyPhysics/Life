/**
* Author: Chididebere Okafor 
* 
* Purpose: This program runs a simulation of Conway’s Game of Life using the
* Landscape and LandscapeDisplay classes. The simulation evolves a
* grid of cells over time according to the standard Game of Life rules.
*
*/
public class LifeSimulation{

    

    public static void main(String[] args) throws InterruptedException {

        int rows = 100;
        int cols = 100;
        double chance = 0.3;

        Landscape scape = new Landscape(rows, cols, chance);

        LandscapeDisplay display = new LandscapeDisplay(scape, 6);

        // while (true) {
        //     scape.advance();
        //     display.repaint();
        //     Thread.sleep(250);

        // }

        for (int i = 0; i < 1000; i++){
            scape.advance();
            display.repaint();
            Thread.sleep(250);
        }

    }


    // public static void main(String[] args) {

    //     int rows = 100;
    //     int cols = 100;

    //     int rounds = 1000;
    //     int trials = 20;   // number of simulations per probability

    //     System.out.println("Probability,AverageLivingCells");

    //     // probabilities from 0.0 to 1.0
    //     for (double p = 0.0; p <= 1.0; p += 0.05) {

    //         int totalLiving = 0;

    //         for (int t = 0; t < trials; t++) {

    //             Landscape scape = new Landscape(rows, cols, p);

    //             // run simulation
    //             for (int i = 0; i < rounds; i++) {
    //                 scape.advance();
    //             }

    //             totalLiving += scape.getLivingCells();
    //         }

    //         double average = (double) totalLiving / trials;

    //         System.out.println(p + "," + average);
    //     }
    // }
}

