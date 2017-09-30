/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distances;

/**
 * Euclidean distance strategy.
 *
 * @author Robert Streetman
 */
public class EuclideanDistanceStrategy implements DistanceStrategy {
    
    @Override
    public double distance(double[] pointA, double[] pointB) {
        double sumSquared = 0;
        
        for (int i = 0; i < pointA.length; i++) {
            sumSquared += (pointA[i] - pointB[i]) * (pointA[i] - pointB[i]);
        }
        
        return Math.sqrt(sumSquared);
    }
}
