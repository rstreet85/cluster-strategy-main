#cluster-strategy-java
A Java implementation of various clustering algorithms using Strategy design pattern. Naive k-means is the first algorithm supported.

Example usage can be found in `Main.java`:

```Java
ClusterContext cContext = new ClusterContext();
cContext.setClusterStrategy(new KmeansNaiveStrategy());
List<double[]> centroids = cContext.getCentroids(rawData, NUM_CLUSTERS);
```
