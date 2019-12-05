#%% 
# Reading data file

import pandas as pd 
import numpy as np 

data = pd.read_csv("clusters_cologne.csv")
data = data[['LAT','LONG']].to_numpy()

# %%
# Using Pyclustering to find silhouette index
from pyclustering.cluster.center_initializer import kmeans_plusplus_initializer
from pyclustering.cluster.kmeans import kmeans
from pyclustering.cluster.silhouette import silhouette
from pyclustering.cluster.silhouette import silhouette_ksearch_type, silhouette_ksearch
from pyclustering.utils.metric import distance_metric, type_metric

# Prepare initial centers
centers = kmeans_plusplus_initializer(data, 4).initialize()
manhattan_metric = distance_metric(type_metric.MANHATTAN)
kmeans_instance = kmeans(data, centers, metric = manhattan_metric)
kmeans_instance.process()
clusters = kmeans_instance.get_clusters()
# # Calculate Silhouette score
# score = silhouette(data, clusters).process().get_score()

# %%
search_instance = silhouette_ksearch(data, 2, 10, algorithm=silhouette_ksearch_type.KMEANS).process()
amount = search_instance.get_amount()
scores = search_instance.get_scores()
print("Scores: '%s'" % str(scores))


#%%
 
scores


# %%
import numpy as np 
import matplotlib.pyplot as plt

x = np.arange(2, 10, 1)


plt.xlabel('Nº Clusters')
plt.ylabel('Índice de Silhueta')
plt.title('Avaliação dos clusters')
plt.plot(x,scores) 

plt.savefig('colonia_silhueta.png')

# %%
kmeans_instance.get_centers()
#%%


# %%
# Using Sklearn to find silhouette index
# import numpy as np
# from sklearn.cluster import KMeans

# kmeans_model = KMeans(n_clusters=3, random_state=1).fit(X)
# labels = kmeans_model.labels_
# metrics.silhouette_score(X, labels, metric='euclidean')