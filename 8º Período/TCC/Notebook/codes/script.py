
#%%
import pandas as pd
 
in_filename = ('Gluciano.txt')
out_filename = ('Output.csv')
 
df = pd.read_csv(in_filename, sep=" ")
df.to_csv(out_filename, index=False)

#%%
