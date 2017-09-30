# cluster-strategy-java
A Java implementation of various clustering algorithms using Strategy design pattern. Naive k-means is the first algorithm supported.

Example usage can be found in `Main.java`:

```Java
//Demonstrates use of naive k-means strategy pattern
ClusterContext cContext = new ClusterContext();
cContext.setClusterStrategy(new KmeansNaiveStrategy());
List<double[]> centroids = cContext.findCentroids(rawData, NUM_CLUSTERS);

//Print output from naive k-means clustering
System.out.format("%n%nMain.java: Cluster Centroids...%n-------------------------------%n");
centroids.forEach(centroid -> {
    System.out.format("Centroid: %s%n", Arrays.toString(centroid));
});
System.out.format("%n%n");
```
