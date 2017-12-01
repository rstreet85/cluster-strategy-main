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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import distances.*;

/**
 * Strategy for naive k-means clustering. The naive version picks seeds at random.
 *
 * @author Robert Streetman
 */
public class KmeansNaiveStrategy implements ClusterStrategy {
    private final int DEF_ITERATIONS = 10;
    
    private DistanceContext distContext;    //Access distance measures with Startegy patterns
    private List<double[]> centroids;       //Return values
    private Cluster[] clusters;             //Cluster objects to hold temp centroids, etc...
    private double[][] data;                //Input data values
    private int numClusters;                //Number of clusters into which data is sorted
    
    @Override
    //TODO: allow users to pass in desired number of iterations, though 5-7 are usually sufficient...
    public List<double[]> findCentroids(double[][] data, int n_clusters) {
        //TODO:For tracing only, comment out when done testing...
        System.out.format("%nBeginning k-means clustering...%n");
        
        this.data = data;
        this.numClusters = n_clusters;
        centroids = new ArrayList();
        clusters = new Cluster[numClusters];
        
        //TODO:For tracing only, comment out when done testing...
        System.out.format("Number of clusters: %d, number of data points: %d...%n%n", numClusters, data.length);
        
        //Initialize DistanceContext.
        //TODO: allow user to pass selected distance strategy
        distContext = new DistanceContext();
        distContext.setDistanceStrategy(new EuclideanDistanceStrategy());
        
        //Step 1. Seed centroids before clustering
        initializeCentroids();
        
        //Step 2. Cluster (Default: 10 iterations
        for (int i = 0; i < DEF_ITERATIONS; i++) {
            cluster();
        }
        
        //Step 3. Assign final cluster centroids to output variable
        for (Cluster cl : clusters) {
            centroids.add(cl.Centroid());
        }
        
        //For tracing only, comment out when done
        System.out.format("Cluster centroids:%n------------------%n");
        centroids.forEach(centroid -> {
            System.out.format("Centroid: %s%n", Arrays.toString(centroid));
        });
        
        return centroids;
    }
    
    /**
     * This method picks k unique random integers in the range [0, n], where k=number of clusters and n = number of points.
     */
    private void initializeCentroids() {
        
        List<Integer> seeds = new ArrayList();
        Random randGen = new Random();
        
        //Seed centroids with randomly selected input points...
        for (int i = 0; i < numClusters; i++) {
            if (i == 0) {
                seeds.add(randGen.nextInt(data.length));
            } else {
                int rand = randGen.nextInt(data.length);
                
                //Keep generating a new random number as long as they already exist in list of seeds.
                while (seeds.contains(rand)) {
                    rand = randGen.nextInt(data.length);
                }
                
                seeds.add(rand);
            }
        }
        
        //Assign centroid values to each cluster...
        for (int i = 0; i < numClusters; i++) {
            clusters[i] = new Cluster(data[seeds.get(i)]);
        }
    }
    
    /**
     * Standard clustering step: 1) Assign points to existing centroids, 2) find new centroid, 3) set new centroid.
     */
    
    private void cluster() {
        int minIndex;
        double minDist;
        
        //Assign each point to the nearest centroid
        for (double[] point : data) {
            minIndex = 0;    //TODO: Default to first centroid until testing....
            minDist = Double.MAX_VALUE;
            
            //Find distance to each centroid, keep track of nearest.
            for (int i = 0; i < numClusters; i++) {
                double centroidDist = distContext.getDistance(point, clusters[i].Centroid());
                
                if (centroidDist < minDist) {
                    minDist = centroidDist;
                    minIndex = i;
                }
            }
            
            clusters[minIndex].Insert(point);
        }
        
        //TODO:This variable tracks total SSE (sum of all cluster's SSE)
        double sseTotal = 0;
        System.out.format("New Iteration...%n");
        //Assign new centroid to each cluster
        for (Cluster cl : clusters) {
            cl.CalcCentroid();
            
            //TODO:For testing, print out SSE before clearing
            double sse = cl.SumSquareError();
            sseTotal += sse;
            System.out.format("Cluster SSE: %f%n", sse);
            
            cl.ClearPoints();   //TODO: Maybe cluster should auto clear its points after finding new centroid.
        }
        
        System.out.format("%nTotal SSE: %f%n----------------------%n%n", sseTotal);
    }
}
