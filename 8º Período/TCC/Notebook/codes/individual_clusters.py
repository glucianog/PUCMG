#%%
from pyclustering.cluster.kmeans import kmeans, kmeans_visualizer
from pyclustering.cluster.center_initializer import kmeans_plusplus_initializer
from pyclustering.utils.metric import distance_metric, type_metric
import numpy as np
import pandas as pd

data = pd.read_csv("roma_calibrated.csv")
data = data[['id','time', 'lat_y','long_x']]

clusters = []

cars_id = np.unique(np.array(data[['id']]))

# %%
for idx in cars_id:
    auxdata = data.loc[data['id'] == idx]
    auxdata = np.array(data[['lat_y', 'long_x']])
    manhattan_metric = distance_metric(type_metric.MANHATTAN)
    initial_centers = kmeans_plusplus_initializer(auxdata, 10).initialize()
    kmeans_instance = kmeans(auxdata, initial_centers, metric = manhattan_metric)
    kmeans_instance.process()
    if len(clusters) == 0:
        clusters = kmeans_instance.get_centers()
    else:
        clusters = np.append(clusters, kmeans_instance.get_centers())


# %%
lat = clusters[0::2]
lon = clusters[1::2]

clusterData = {'LAT': lat, 'LONG': lon}
clusters_df = pd.DataFrame(data = clusterData)
clusters_df.to_csv('clusters.csv')


# %%
clusters_df



#%%
