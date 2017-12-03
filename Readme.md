# Java Data Clustering
## Data clustering in Java using Strategy design patterns.
A project to build a pure Java library to cluster data using design patterns. My goals were to
practice design patterns and write collection of data mining tools. The first clustering algorithms
are for partitional data clustering, specifically various implementations of the [k-means algorithm](https://en.wikipedia.org/wiki/K-means_clustering).
Additionally, I'm adding code for numeric + text distance measurements and statistical normalization.
The sample data this project was based on can be found at the [UCI Machine Learning Repo](https://archive.ics.uci.edu/ml/index.php).

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
