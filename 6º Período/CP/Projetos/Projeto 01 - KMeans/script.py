import random

path = "c:/Users/gabri/Desktop/inputs/"

pontos    = 50000
dimensao  = 3
clusters  = 20
iteracoes = 100
flagnome  = 0

file_name = "pub.in"
with open(path + file_name, "w") as f:
    f.write("{} {} {} {} {}\n".format(pontos, dimensao, clusters, iteracoes, flagnome))
    for _ in range(pontos):
        valor1 = random.uniform(0.0, 10.0)
        valor2 = random.uniform(0.0, 10.0)
        valor3 = random.uniform(0.0, 10.0)
        valor4 = random.uniform(0.0, 10.0)
        f.write("{:.1f} {:.1f} {:.1f} {:.1f} \n".format(valor1, valor2, valor3, valor4))
