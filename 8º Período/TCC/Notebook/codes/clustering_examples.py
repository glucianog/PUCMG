# %%
import numpy as np 
import pandas as pd
import matplotlib.pyplot as pyplot

# %% [markdown]
# # Reading Data file

# %%
data = pd.read_csv("roma_calibrated.csv")
data = data[['id','lat_y','long_x']]
data.shape # Number of instances, number of atributes

# %% [markdown]
# # Data to be clustered
# %%
cluster_data = np.array(data[['lat_y', 'long_x']])
#%% [markdown]
# # Clustering the database with XMeans using PyClustering


#%%
from pyclustering.cluster import cluster_visualizer
from pyclustering.cluster.xmeans import xmeans
from pyclustering.cluster.center_initializer import kmeans_plusplus_initializer

amount_initial_centers = 2

# Prepare initial centers - amount of initial centers defines amount of clusters from which X-Means will
# start analysis.
initial_centers = kmeans_plusplus_initializer(cluster_data, amount_initial_centers).initialize()

# Create instance of X-Means algorithm. The algorithm will start analysis from 2 clusters, the maximum
# number of clusters that can be allocated is 20.
xmeans_instance = xmeans(cluster_data, initial_centers, 100)
xmeans_instance.process()

# Extract clustering results: clusters and their centers
clusters = xmeans_instance.get_clusters()
centers = xmeans_instance.get_centers()

# Visualize clustering results
visualizer = cluster_visualizer()
#visualizer.append_clusters(clusters, cluster_data)
visualizer.append_cluster(centers, None, marker='*', markersize=10)
visualizer.show()



#%% [markdown]
# # Clustering using XMeans once more trying to find better results on the previous centers

amount_initial_centers = 10

# Prepare initial centers - amount of initial centers defines amount of clusters from which X-Means will
# start analysis.
initial_centers = kmeans_plusplus_initializer(centers, amount_initial_centers).initialize()

# Create instance of X-Means algorithm. The algorithm will start analysis from 2 clusters, the maximum
# number of clusters that can be allocated is 20.
xmeans_instance = xmeans(centers, initial_centers, 100)
xmeans_instance.process()

# Extract clustering results: clusters and their centers
clusters = xmeans_instance.get_clusters()
centers = xmeans_instance.get_centers()

# Visualize clustering results
visualizer = cluster_visualizer()
#visualizer.append_clusters(clusters, cluster_data)
visualizer.append_cluster(centers, None, marker='*', markersize=10)
visualizer.show()


# %% [markdown]

# # Saving clusters into a csv
centers_df = pd.DataFrame(data = centers)
centers_df.to_csv('global_clusters.csv')


#%%


#%%
from pyclustering.cluster import cluster_visualizer
from pyclustering.cluster.xmeans import xmeans
from pyclustering.cluster.center_initializer import kmeans_plusplus_initializer

amount_initial_centers = 2

# Prepare initial centers - amount of initial centers defines amount of clusters from which X-Means will
# start analysis.
initial_centers = kmeans_plusplus_initializer(cluster_data, amount_initial_centers).initialize()

# Create instance of X-Means algorithm. The algorithm will start analysis from 2 clusters, the maximum
# number of clusters that can be allocated is 20.
xmeans_instance = xmeans(cluster_data, initial_centers, 20)
xmeans_instance.process()

# Extract clustering results: clusters and their centers
clusters = xmeans_instance.get_clusters()
centers = xmeans_instance.get_centers()

# Visualize clustering results
visualizer = cluster_visualizer()
#visualizer.append_clusters(clusters, cluster_data)
visualizer.append_cluster(centers, None, marker='*', markersize=10)
visualizer.show()


#%%
