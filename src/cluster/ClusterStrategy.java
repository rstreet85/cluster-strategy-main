package cluster;

import java.util.List;

/**
 * Cluster Strategy interface.
 *
 * @author Robert Streetman
 */
public interface ClusterStrategy {
    public List<double[]> findCentroids(double[][] data, int clusters);
}
