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
Result:
```
Beginning k-means clustering...
Number of clusters: 3, number of data points: 150...

New Iteration...
Cluster SSE: 4.966278
Cluster SSE: 288.093598
Cluster SSE: 121.940705

Total SSE: 415.000581
----------------------

New Iteration...
Cluster SSE: 30.026815
Cluster SSE: 98.679857
Cluster SSE: 14.006869

Total SSE: 142.713541
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 59.490720
Cluster SSE: 18.379421

Total SSE: 101.955403
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 53.029480
Cluster SSE: 21.902186

Total SSE: 99.016928
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 48.858549
Cluster SSE: 25.212626

Total SSE: 98.156436
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 43.712415
Cluster SSE: 30.148729

Total SSE: 97.946406
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 39.848696
Cluster SSE: 33.579068

Total SSE: 97.513026
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 35.155764
Cluster SSE: 38.088031

Total SSE: 97.329057
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 32.391083
Cluster SSE: 40.729503

Total SSE: 97.205847
----------------------

New Iteration...
Cluster SSE: 24.085262
Cluster SSE: 29.534177
Cluster SSE: 43.505612

Total SSE: 97.125051
----------------------

Cluster centroids:
------------------
Centroid: [5.005999999999999, 3.428000000000001, 1.4620000000000002, 0.2459999999999999]
Centroid: [6.827499999999999, 3.0699999999999994, 5.699999999999998, 2.062499999999999]
Centroid: [5.885000000000001, 2.74, 4.376666666666667, 1.4183333333333332]


Main.java: Cluster Centroids...
-------------------------------
Centroid: [5.005999999999999, 3.428000000000001, 1.4620000000000002, 0.2459999999999999]
Centroid: [6.827499999999999, 3.0699999999999994, 5.699999999999998, 2.062499999999999]
Centroid: [5.885000000000001, 2.74, 4.376666666666667, 1.4183333333333332]
```