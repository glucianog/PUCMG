import random

path = "c:/Users/gabri/Desktop/PUCMG/5º Período/PAA/TP 01/inputs/"

days = [i for i in range(1, 22)]
dishes = [i for i in range(1, 51)]
budget = [i for i in range(0, 101)]

max_days = days[len(days) - 1]
max_dishes = dishes[len(dishes) - 1]
max_budget = budget[len(budget) - 1]

min_cost = 1
max_cost = 50
min_benefit = 1
max_benefit = 1000

# Variando dias
for day in days:
    file_name = "{}_{}_{}.in".format(day, max_dishes, max_budget)
    with open(path + file_name, "w") as f:
        f.write("{} {} {}\n".format(day, max_dishes, max_budget))
        for dish in range(max_dishes):
            cost = random.randint(min_cost, max_cost + 1)
            benefit = random.randint(min_benefit, max_benefit + 1)
            f.write("{} {}\n".format(cost, benefit))
        f.write("0 0 0\n")


# Variando nº de pratos
for dish in dishes:
    file_name = "{}_{}_{}.in".format(max_days, dish, max_budget)
    with open(path + file_name, "w") as f:
        f.write("{} {} {}\n".format(max_days, dish, max_budget))
        for d in range(dish):
            cost = random.randint(min_cost, max_cost + 1)
            benefit = random.randint(min_benefit, max_benefit + 1)
            f.write("{} {}\n".format(cost, benefit))
        f.write("0 0 0\n")

# Variando orcamento
for b in budget:
    file_name = "{}_{}_{}.in".format(max_days, max_dishes, b)
    with open(path + file_name, "w") as f:
        f.write("{} {} {}\n".format(max_days, max_dishes, b))
        for dish in range(max_dishes):
            cost = random.randint(min_cost, max_cost + 1)
            benefit = random.randint(min_benefit, max_benefit + 1)
            f.write("{} {}\n".format(cost, benefit))
        f.write("0 0 0\n")