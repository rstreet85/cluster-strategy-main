/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distances;

/**
 * Manhattan distance strategy.
 *
 * @author Robert Streetman
 */
public class ManhattanDistanceStrategy implements DistanceStrategy {
    
    @Override
    public double distance(double[] pointA, double[] pointB) {
        double sumDiff = 0;
        
        for (int i = 0; i < pointA.length; i++) {
            sumDiff += Math.abs(pointA[i] - pointB[i]);
        }
        
        return sumDiff;
    }
    
}
