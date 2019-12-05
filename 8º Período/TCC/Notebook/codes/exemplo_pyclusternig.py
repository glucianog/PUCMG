#%%
from pyclustering.cluster.center_initializer import kmeans_plusplus_initializer
from pyclustering.cluster.kmeans import kmeans
from pyclustering.cluster.silhouette import silhouette
from pyclustering.samples.definitions import SIMPLE_SAMPLES
from pyclustering.utils import read_sample

# Read data 'SampleSimple3' from Simple Sample collection.
sample = read_sample(SIMPLE_SAMPLES.SAMPLE_SIMPLE3)
# %%

#%%
sample


# %%

# %%

#%%
# Prepare initial centers
centers = kmeans_plusplus_initializer(sample, 4).initialize()
# Perform cluster analysis
kmeans_instance = kmeans(sample, centers)
kmeans_instance.process()
clusters = kmeans_instance.get_clusters()
# Calculate Silhouette score
score = silhouette(sample, clusters).process().get_score()