"""
The Simplified Whiskas Model Python Formulation for the PuLP Modeller

Authors: Antony Phillips, Dr Stuart Mitchell  2007
"""
# Import PuLP modeler functions
from pulp import *

# Create the 'prob' variable to contain the problem data
prob = LpProblem("Group tasks",LpMaximize)

# The 2 variables Beef and Chicken are created with a lower limit of zero
x1t1 = LpVariable("Student 1 - Task 1", 0 , 1, LpBinary)
x1t2 = LpVariable("Student 1 - Task 2", 0 , 1, LpBinary)

x2t1 = LpVariable("Student 2 - Task 1", 0 , 1, LpBinary)
x2t2 = LpVariable("Student 2 - Task 2", 0 , 1, LpBinary)

    # The objective function is added to 'prob' first
prob += 3*x1t1 + 5*x1t2 \
     + 8*x2t1 + 5*x2t2, "Allocated members"

# The five constraints are entered
prob += x1t1 + x1t2  >= 1, "Min tasks to member A"
prob += x2t1 + x2t2  >= 1, "Min tasks to member B"


prob += 3*x1t1 + 8*x2t1  >= 1, "Min complexity to task 1" 
prob += 5*x1t2 + 5*x2t2  >= 1, "Min complexity to task 2"

prob += 3*x1t1 + 8*x2t1  <= 13, "Max complexity to task 1"
prob += 5*x1t2 + 5*x2t2  <= 8,  "Max complexity to task 2"

# The problem data is written to an .lp file
prob.writeLP("GroupTasksModel.lp")

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


