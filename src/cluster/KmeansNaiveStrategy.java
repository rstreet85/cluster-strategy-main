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

import distances.DistanceContext;
import distances.EuclideanDistanceStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
//import distances.*;

/**
 * Strategy for naive <a href="https://en.wikipedia.org/wiki/K-means_clustering">k-means clustering</a>.
 * K-means is a partitional clustering algorithm based on finding prototypes of each label. The naive version
 * picks centroid seeds at random from the data set.
 *
 * @author Robert Streetman
 */
public class KmeansNaiveStrategy implements PartitionalClusterStrategy {
    private static final Logger KMEANS_LOGGER = Logger.getLogger(KmeansNaiveStrategy.class.getName());
    private final int DEF_ITERATIONS = 10;  //It's rare that reaching a local minimum takes more than 5-10 iterations.
    
    private DistanceContext distContext;    //Access distance measures with Startegy patterns
    private List<double[]> centroids;       //Return values
    private Cluster[] clusters;             //Cluster objects to hold temp centroids, etc...
    private double[][] data;                //Input data values
    private int numClusters;                //Number of clusters into which data is sorted
    
    /**
     * @param data          Array of numeric data to be clustered.
     * @param n_clusters    Number of labels in data set.
     * @return List         List of centroids after clustering
     */
    //TODO: Allow users to pass in desired number of iterations, though 5-7 are usually sufficient...
    //TODO: Allow users to pass in desired distance strategy. Using Euclidean for now.
    @Override
    public List<double[]> findCentroids(double[][] data, int n_clusters) {
        //TODO:For tracing only, comment out when done testing...
        //System.out.format("%nBeginning k-means clustering...%n");
        KMEANS_LOGGER.log(Level.INFO, String.format("Beginning k-means clustering..."));
        
        this.data = data;
        this.numClusters = n_clusters;
        centroids = new ArrayList();
        clusters = new Cluster[numClusters];
        
        //TODO:For tracing only, comment out when done testing...
        //System.out.format("Number of clusters: %d, number of data points: %d...%n%n", numClusters, data.length);
        KMEANS_LOGGER.log(Level.INFO, String.format("Number of clusters: %d, number of data points: %d...",
                numClusters, data.length));
        
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
        
        //TODO:For tracing only, comment out when done testing...
        //System.out.format("Cluster centroids:%n------------------%n");
        KMEANS_LOGGER.log(Level.INFO, String.format("Cluster centroids:%n------------------"));
        
        centroids.forEach(centroid -> {
            //System.out.format("Centroid: %s%n", Arrays.toString(centroid));
            KMEANS_LOGGER.log(Level.INFO, String.format("Centroid: %s", Arrays.toString(centroid)));
        });
        
        return centroids;
    }
    
    /**
     * This method picks k unique random integers in the range [0, n], where k=number_of_clusters and n=number_of_points.
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
     * Standard clustering step:
     * 1) Assign points to existing centroids
     * 2) find new centroid
     * 3) set new centroid.
     */    
    private void cluster() {
        int minIndex;   //Index of the nearest cluster discovered.
        double minDist; //Distance to nearest cluster discovered.
        
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
        
        //TODO:For tracing only, comment out when done testing...
        //System.out.format("New Iteration...%n");
        KMEANS_LOGGER.log(Level.INFO, String.format("New Iteration..."));
        
        //Assign new centroid to each cluster
        for (Cluster cl : clusters) {
            cl.CalcCentroid();
            
            //TODO:For testing, print out SSE before clearing
            double sse = cl.SumSquareError();
            sseTotal += sse;
            //TODO:For tracing only, comment out when done testing...
            //System.out.format("Cluster SSE: %f", sse);
            KMEANS_LOGGER.log(Level.INFO, String.format("Cluster SSE: %f", sse));
            
            //Clear the points associated with previous centroid.
            cl.ClearPoints();
        }
        
        //TODO:For tracing only, comment out when done testing...
        //System.out.format("%nTotal SSE: %f%n----------------------%n%n", sseTotal);
        KMEANS_LOGGER.log(Level.INFO, String.format("Total SSE: %f%n----------------------", sseTotal));
    }
}
