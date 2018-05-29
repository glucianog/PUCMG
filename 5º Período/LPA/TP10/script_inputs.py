import random

path = "c:/Users/gabri/Desktop/PUCMG/5º Período/LPA/TP10/inputs/"

min_tarefas = 1
max_tarefas = 150

min_dificuldade = 1
max_dificuldade = 2000

#Variando Nº Tarefas
file_name = "pub.in"
with open(path + file_name, "w") as f:      
    for t in range (1,10):  
        n_tarefas = random.randint(min_tarefas, max_tarefas)
        f.write("{}\n".format(n_tarefas))
        for x in range(n_tarefas):
            peso = random.randint(min_dificuldade,max_dificuldade)
            f.write("{} ".format(peso))
        f.write("\n")        



    