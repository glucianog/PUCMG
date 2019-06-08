"""
The Group tasks optimization model

Authors: Gabriel Luciano, Geovane Fonseca, Luigi Domenico
"""
# Import PuLP modeler functions
from pulp import *

# Create the 'prob' variable to contain the problem data
prob = LpProblem("Group tasks",LpMinimize)

# The 2 variables student x task are created with a lower limit of zero
y1  = LpVariable("Y1", 0 , 1, LpBinary)
y2  = LpVariable("Y2", 0 , 1, LpBinary)
y3  = LpVariable("Y3", 0 , 1, LpBinary)
y4  = LpVariable("Y4", 0 , 1, LpBinary)
y5  = LpVariable("Y5", 0 , 1, LpBinary)
y6  = LpVariable("Y6", 0 , 1, LpBinary)

# The objective function is added to 'prob' first
prob += - y1 - y2 - y3 - y4 + 13*y5 + 8*y6, "W"

# The five constraints are entered

prob += - y1 - 3*y3  + 3*y5 >= 3, "R1"
prob += - y1 - 5*y4  + 5*y6 >= 5, "R2"
prob += - y2 - 8*y3  + 8*y5 >= 8, "R3"
prob += - y2 - 5*y4  + 5*y6 >= 5, "R4"


# The problem data is written to an .lp file
prob.writeLP("GroupTasksModelDual.lp")

# The problem is solved using PuLP's choice of Solver
prob.solve(pulp.GLPK())

# The status of the solution is printed to the screen
print("Status:", LpStatus[prob.status])

# Each of the variables is printed with it's resolved optimum value
for v in prob.variables():
    print(v.name, "=", v.varValue)

# The optimised objective function value is printed to the screen
print("Total of complexities expended on tasks = ", value(prob.objective))


# https://pythonhosted.org/PuLP/CaseStudies/a_blending_problem.html


