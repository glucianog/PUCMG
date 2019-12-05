# To add a new cell, type '#%%'
# To add a new markdown cell, type '#%% [markdown]'
#%% [markdown]
# # Reading Map API Key
# 

#%%
import os

here = os.path.dirname(os.path.abspath('__file__'))

filename = 'apikey.txt'

with open(filename) as f:
    api_key = f.readline()
    f.close()

#%% [markdown]
# # Activating Google Maps API itself

#%%
import gmaps
gmaps.configure(api_key=api_key) # Fill in with your API key


#%% [markdown]
# # Displaying locations with dots

#%%

import pandas as pd
import gmaps.datasets
from ipywidgets.embed import embed_minimal_html
df = pd.read_csv('clusters_cologne.csv')
dataframe  = df[['LAT', 'LONG']]

clusters_layer = gmaps.symbol_layer(
    dataframe, fill_color="red", stroke_color="red", stroke_opacity=0.0, scale=30, fill_opacity=0.6
)

fig = gmaps.figure()
fig.add_layer(clusters_layer)


embed_minimal_html('export.html', views=[fig])
# %%

# #%% [markdown]
# # # Heatmap that may be useful

# #%%
# # Get the dataset
# earthquake_df = gmaps.datasets.load_dataset_as_df('earthquakes')
# #Get the locations from the data set
# locations = earthquake_df[['latitude', 'longitude']]
# #Get the magnitude from the data
# weights = earthquake_df['magnitude']
# #Set up your map
# fig = gmaps.figure()


# fig.add_layer(gmaps.heatmap_layer(locations, weights=weights))
# fig


# #%%
# #Define location 1 and 2
# Durango = (37.2753,-107.880067)
# SF = (37.7749,-122.419416)
# #Create the map
# fig = gmaps.figure()
# #create the layer
# layer = gmaps.directions.Directions(Durango, SF,mode='car')
# #Add the layer
# fig.add_layer(layer)
# fig


