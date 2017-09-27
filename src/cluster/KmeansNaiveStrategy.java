package cluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Strategy for naive k-means clustering. The naive version picks seeds at random.
 *
 * @author Robert Streetman
 */
public class KmeansNaiveStrategy implements ClusterStrategy {
    private List<double[]> centroids;
    private double[][] data;
    private int clusters;
    
    public List<double[]> cluster(double[][] data, int clusters) {
        //For tracing only, comment out when done
        System.out.println("Beginning k-means clustering...");
        
        centroids = new ArrayList();
        this.data = data;
        this.clusters = clusters;
        
        //Step 1. Seed centroids before clustering
        initializeCentroids();
        
        //Step 2. Cluster (Default: 5 iterations
        
        //For tracing only, comment out when done
        System.out.println("Centroid Seeds:%n---------------%n");
        centroids.forEach(centroid -> {
            System.out.format("Centroid: %s", Arrays.toString(centroid));
        });
        
        return centroids;
    }
    
    /**
     * This method picks k unique random integers in the range [0, n], where k=number of clusters and n = number of points.
     */
    private void initializeCentroids() {
        List<Integer> seeds = new ArrayList();
        Random randGen = new Random();
        
        for (int i = 0; i < clusters; i++) {
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
    }
    
    /**
     * Internal Euclidean distance function. Once complete, this can be replaced by strategy patterns as well.
     * 
     * @deprecated 
     * @param a
     * @param b
     * @return 
     */
    private double euclidDist(double[] a, double[] b) {
        double sum = 0.;
        
        for (int i = 0; i < a.length; i++) {
            double diff = Math.abs(a[i] - b[i]);
            sum += diff * diff;
        }
        
        return Math.sqrt(sum);
    }    
}
