package cluster;

import java.util.List;

/**
 * Cluster Strategy interface.
 *
 * @author Robert Streetman
 */
public interface ClusterStrategy {
    public List<double[]> cluster(double[][] data, int clusters);
}
