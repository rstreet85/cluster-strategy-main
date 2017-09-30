package cluster;

import java.util.ArrayList;
//Imports used only for testing
import distances.*;

/**
 * This class holds representation of a data cluster.
 * 
 * @author Robert Streetman
 */
public class Cluster {
    private final int dimensions;
    
    private ArrayList<double[]> points;
    private double[] centroid;
    //Variables used for testing only
    private DistanceContext distContext;
    
    /**
     * Cluster instantiation: Centroid must be determined in advance.
     * 
     * @param c A double array representing the coordinates of the cluster centroid (average). 
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
     * Returns the sum of squared errors: The lower this value, the more compact the cluster; This value
     * should decrease as clustering progresses, until reaching a local min.
     * 
     * @return The sum of the squared distance from each point to the centroid.
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
     * Clears points in the cluster before each iteration.
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