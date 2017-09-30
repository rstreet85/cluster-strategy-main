package cluster;

import java.util.List;

/**
 * Context for manipulating ClusterStrategy.
 *
 * @author Robert Streetman
 */
public class ClusterContext {
    private ClusterStrategy strategy;
    
    public void setClusterStrategy(ClusterStrategy cStrat) {
        strategy = cStrat;
    }
    
    //The output of all clustering algorithms will be only the final centroids.
    public List<double[]> findCentroids(double[][] data, int clusters) {
        return strategy.findCentroids(data, clusters);
    }
}
