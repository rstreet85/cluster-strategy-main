/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distances;

/**
 * Distance context for choosing different strategies.
 *
 * @author Robert Streetman
 */
public class DistanceContext {
    private DistanceStrategy strategy;
    
    public void setDistanceStrategy(DistanceStrategy strat) {
        strategy = strat;
    }
    
    public double getDistance(double[] pointA, double[] pointB) {
        return strategy.distance(pointA, pointB);
    }
}
