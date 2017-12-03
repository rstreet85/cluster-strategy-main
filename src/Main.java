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
import cluster.PartitionalClusteringContext;
import cluster.KmeansNaiveStrategy;
import dataio.DataFileReader;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * This program demonstrates the use of a Strategy design pattern to perform data clustering on numeric data.
 *
 * @author Robert Streetman
 */
public class Main {
    /*
        Clustering parameters for the data file included with this example. The file bezdek_iris contains
        3 labelled classes.
    */
    private static final String TEST_FILE = "data/bezdekIris_noLabel_k=3.data";
    private static final int NUM_CLUSTERS = 3;

    /**
     * @param args The command line arguments; No arguments required.
     */
    public static void main(String[] args) {
        double[][] rawData = DataFileReader.ReadCSVFile(new File(TEST_FILE));
        
        //Demonstrates use of naive k-means strategy pattern
        PartitionalClusteringContext cContext = new PartitionalClusteringContext();
        cContext.setClusterStrategy(new KmeansNaiveStrategy());
        List<double[]> centroids = cContext.findCentroids(rawData, NUM_CLUSTERS);
        
        //Print output from naive k-means clustering
        System.out.format("%n%nMain.java: Cluster Centroids...%n-------------------------------%n");
        centroids.forEach(centroid -> {
            System.out.format("Centroid: %s%n", Arrays.toString(centroid));
        });
        System.out.format("%n%n");
    }
    
}
