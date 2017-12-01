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
