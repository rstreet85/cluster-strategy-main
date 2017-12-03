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

import java.util.ArrayList;
//Imports used only for internally calculating SSE
import distances.*;

/**
 * Class to represent a single partitional data cluster object and its methods.
 * 
 * @author Robert Streetman
 */
public class Cluster {
    private final int dimensions;       //Number of dimensions in each data point.
    
    private ArrayList<double[]> points; //Temporary list of data points associated with this centroid.
    private double[] centroid;          //Coordinates of this cluster's centroid.
    //Variables used only for internally calculating SSE
    private DistanceContext distContext;//Used to interact with Distance Starteg pattern.
    
    /**
     * Method to instantiate the Cluster object.
     * 
     * @param c A double array representing the coordinates of the cluster centroid (prototype). 
     */
    public Cluster(double[] c) {
        dimensions = c.length;
        centroid = c;
        points = new ArrayList<>();
        
        distContext = new DistanceContext();
        distContext.setDistanceStrategy(new EuclideanDistanceStrategy());
    }
    
    /**
     * Retrieve centroid (average) of the cluster as a double[] of coordinate values.
     * 
     * @return Cluster centroid.
     */
    public double[] Centroid() {
        return centroid;
    }
    
    /**
     * Set cluster centroid with coordinates of given centroid.
     * 
     * @param c Array of coordinates expressed as double.
     */
    public void SetCentroid(double[] c) {
        centroid = c;
    }
    
    /**
     * Insert given point into cluster.
     * 
     * @param point Array of point coordinates expressed as double[].
     */
    public void Insert(double[] point) {
        points.add(point);
    }
    
    /**
     * Returns the sum of squared errors: The lower this value, the more compact the cluster. The sum
     * of all clusters' SSE should decrease after each iteration, until reaching a local min.
     * 
     * @return double The sum of the squared distance from each point to the centroid.
     */
    public double SumSquareError() {
        double sse = 0.;
        
        for (double[] point : points) {
            double dist = distContext.getDistance(point, centroid);
            sse += dist * dist;
        }
        
        return sse;
    }
    
    /**
     * Clears points associated with this centroid before each iteration.
     */
    public void ClearPoints() {
        points = new ArrayList();
    }
    
    /**
     * Calculates and stores the value of the new centroid from all points which have been added to the cluster.
     */
    public void CalcCentroid() {
        centroid = new double[dimensions];
        double[] sum = new double[dimensions];
        int n = 0;  //Keep track of number of points currently in the cluster, this changes every iteration.
        
        for (double[] point : points) {
            for (int d = 0; d < dimensions; d++) {
                sum[d] += point[d];
            }
            
            n++;
        }
        
        for (int d = 0; d < dimensions; d++) {
            centroid[d] = sum[d] / (double) n;
        }
    }
}