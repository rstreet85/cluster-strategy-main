
import cluster.ClusterContext;
import cluster.KmeansNaiveStrategy;
import dataio.DataFileReader;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates use of kmeans clustering with Strategy pattern.
 *
 * @author Robert Streetman
 */
public class Main {
    //These are for the data file to be clustered
    private static final String TEST_FILE = "data/bezdekIris_noLabel_k=3.data";
    private static final int NUM_CLUSTERS = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //This method of reading a file is old, needs to be updated. 
        double[][] rawData = DataFileReader.ReadCSVFile(new File(TEST_FILE));
        
        //Actual use of kmeans strategy pattern
        ClusterContext cContext = new ClusterContext();
        cContext.setClusterStrategy(new KmeansNaiveStrategy());
        List<double[]> centroids = cContext.getCentroids(rawData, NUM_CLUSTERS);
        
        //Print output for validation
        System.out.println("Cluster Centroids:%n-------------------");
        centroids.forEach(centroid -> {
            System.out.format("Centroid: %s%n", Arrays.toString(centroid));
        });
    }
    
}
