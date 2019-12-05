# %%
import pandas as pd 
from pandas.plotting import scatter_matrix

# Reading the data and load it as a DataFrame
df = pd.read_csv('auto-mpg.csv')

# Print out the column names
print('Column names are: ',list(df.columns))

scatter_matrix(df, alpha=0.4,figsize=(7,7))

# Make target (y) equal to mpg
y = df.pop('mpg')

# Make x a large matrix containing displacement, cylinders, weight, acceleration and model year
X = df[['displacement','cylinders','weight','acceleration','model year']]


#%%
# Import the nessecary Library from Sklearn
from sklearn.model_selection import train_test_split

#Split the Data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)

# Import the module
from sklearn.linear_model import LinearRegression

# Initilize the object
reg = LinearRegression()

# Train on the training data
reg.fit(X_train, y_train)

# Predict on the test X set
predictions = reg.predict(X_test)

list(zip(X_train.columns,reg.coef_))

#%%
#import the correct library
import numpy as np

#Caclulate the Mean Error
mean_error = np.sqrt(np.mean((y_test - predictions) ** 2))

#Print out the error in the correct units
print('On Average we where: ',np.round(mean_error),' off from the actual mpg')

def predict(displacement,cylinders,weight,acceleration,model_year):
  
  # Create Predictions
  prediction = reg.predict([[displacement,cylinders,weight,acceleration,model_year]])
  
  print("Your Predicted MPG is: ", np.round(prediction[0]))

displacement = 193
cylinders = 6
weight = 2900
acceleration = 15
model_year = 76

predict(displacement,cylinders,weight,acceleration,model_year)

#%%

displacement = 150
cylinders = 3
weight = 2300
acceleration = 22
model_year = 91

predict(displacement,cylinders,weight,acceleration,model_year)

#%%
