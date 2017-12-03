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
 * Distance Strategy interface for distance measuring algorithms.
 *
 * @author Robert Streetman
 */
public interface DistanceStrategy {
    /**
     * Standard interface to find distance between two points.
     * 
     * @param pointA    Endpoint A
     * @param pointB    Endpoint B
     * @return double   Distance between endpoint A and endpoint B.
     */
    public double distance(double[] pointA, double[] pointB);    
}
