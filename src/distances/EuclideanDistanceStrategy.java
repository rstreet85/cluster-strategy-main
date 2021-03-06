/**
 * Copyright 2017 Robert Streetman
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package distances;

/**
 * Strategy for <a href="https://en.wikipedia.org/wiki/Euclidean_distance">Euclidean Distance</a>.
 * 
 * @author Robert Streetman
 */
public class EuclideanDistanceStrategy implements DistanceStrategy {
    
    /**
     * This method calculates the Euclidean distance between two points of equal dimensionality.
     * 
     * @param pointA    Endpoint A
     * @param pointB    Endpoint B
     * @return double   Euclidean distance between endpoint A and endpoint B.
     */
    @Override
    public double distance(double[] pointA, double[] pointB) {
        double sumSquared = 0;
        
        for (int i = 0; i < pointA.length; i++) {
            sumSquared += (pointA[i] - pointB[i]) * (pointA[i] - pointB[i]);
        }
        
        return Math.sqrt(sumSquared);
    }
}
