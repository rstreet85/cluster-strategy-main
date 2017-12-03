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
package cluster;

import java.util.List;

/**
 * Context for accessing clustering algorithms with a PartitionalClusterStrategy.
 *
 * @author Robert Streetman
 */
public class PartitionalClusteringContext {
    private PartitionalClusterStrategy strategy;
    
    /**
     * Method to set partitional clustering strategy to use.
     * 
     * @param cStrat The desired clustering strategy.
     */
    public void setClusterStrategy(PartitionalClusterStrategy cStrat) {
        strategy = cStrat;
    }
    
    /**
     * Method to find centroids with previously-specified strategy.
     * 
     * @param data      Array of numeric data to be clustered.
     * @param clusters  Number of labels in data set.
     * @return List     List of centroids after clustering.
     */
    public List<double[]> findCentroids(double[][] data, int clusters) {
        return strategy.findCentroids(data, clusters);
    }
}
