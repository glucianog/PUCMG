import plotly as py
import plotly.graph_objs as go

import numpy as np

def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)

# Recuperar valor de Y no gráfico.
def getY(l, x):
    return (np.exp(-l) * (l ** x))/factorial(x)

# Gráfico de probabilidades
def makeProbGraph(l, m):
    x = []
    y = []

    for i in range(0, m):
        x.append(i)
        y.append(getY(l, i))

    trace = go.Scatter(
        x = x,
        y = y,
        name = "(λ) = {}\n".format(l),
        mode = "markers"
    )

    data = [trace]
    layout = go.Layout(
        title='Gráfico da função de probabilidade',
        showlegend=True
    )
    fig = go.Figure(data=data, layout=layout)
    py.offline.plot(fig, filename="poisson_prob.html")

# Gráfico de distribuição acumulada
def makeAccumGraph(l, m):
    x = []
    y = []
    acc = 0

    for i in range(0, m):
        x.append(i)
        acc += getY(l, i);
        y.append(acc)

    trace = go.Scatter(
        x = x,
        y = y,
        mode = 'lines+markes',
        name = "(λ) = {}\n".format(l),
        hoverinfo = y,
        line = dict(
            shape = 'vhv' 
        )

      #  x = x,
      #  y = y,
      #  name= "(λ) = {}\n".format(l),
      #  mode = "lines+markers"
    )

    data = [trace]
    layout = go.Layout(
        title='Gráfico da função de distribuição acumulada',
        showlegend=True
    )
    fig = go.Figure(data=data, layout=layout)
    py.offline.plot(fig, filename="poisson_accum.html")

# Calcular probabilidade acumulada
def calcAccumProb(max, l, min=0):
    i = min
    accum = 0
    while i <= max:
        accum += getY(l, i)
        i += 1

    return accum

# Recuperar probabilidade acumulada
def getAccumProb(op, l):
    if op == 1:
        max = int(input("Insira o valor de x: "))
        accum = calcAccumProb(max = (max - 1), l = l)
        print("Probabilidade acumulada: P(X < {}) = {}".format(max, accum))
    elif op == 2:
        max = int(input("Insira o valor de x: "))
        accum = calcAccumProb(max = max, l = l)
        print("Probabilidade acumulada: P(X > {}) = {}".format(max, 1 - accum))
    elif op == 3:
        max = int(input("Insira o valor de x: "))
        accum = calcAccumProb(max = max, l = l)
        print("Probabilidade acumulada: P(X <= {}) = {}".format(max, accum))
    elif op == 4:
        max = int(input("Insira o valor de x: "))
        accum = calcAccumProb(max = (max - 1), l = l)
        print("Probabilidade acumulada: P(X >= {}) = {}".format(max, 1 - accum))
    elif op == 5:
        min = int(input("Insira o valor de x1: "))
        max = int(input("Insera o valor de x2: "))
        accum = calcAccumProb(min = (min + 1), max = (max - 1), l = l)
        print("Probabilidade acumulada: P({} < X < {}) = {}".format(min, max, accum))
    elif op == 6:
        min = int(input("Insira o valor de x1: "))
        max = int(input("Insera o valor de x2: "))
        accum = calcAccumProb(min = (min + 1), max = max, l = l)
        print("Probabilidade acumulada: P({} < X <= {}) = {}".format(min, max, accum))
    elif op == 7:
        min = int(input("Insira o valor de x1: "))
        max = int(input("Insera o valor de x2: "))
        accum = calcAccumProb(min = min, max = (max - 1), l = l)
        print("Probabilidade acumulada: P({} <= X < {}) = {}".format(min, max, accum))
    elif op == 8:
        min = int(input("Insira o valor de x1: "))
        max = int(input("Insera o valor de x2: "))
        accum = calcAccumProb(min = min, max = max, l = l)
        print("Probabilidade acumulada: P({} <= X <= {}) = {}".format(min, max, accum))
    elif op == 0:
        print(" <- ")
    else:
        print("Opção inválida!\n")

# Menu
def getOption():
    print("+{0}-+".format("-" * 47))
    print("+{0} MENU DE OPÇÕES {0}+".format(" " * 16))
    print("+{0}-+".format("-" * 47))
    print("+ 1. Gráfico da função de probabilidade          +")
    print("+ 2. Gráfico da função de distribuição acumulada +")
    print("+ 3. Cálculo de probabilidade no ponto           +")
    print("+ 4. Cálculo de uma probabilidade acumulada      +")
    print("+ 5. Alterar Valor de Lambda (λ)                 +")
    print("+ 6. Alterar Valor máximo para x                 +")
    print("+ 0. Sair                                        +")
    print("+{0}-+".format("-" * 47))

    return int(input("\nInsira a opção: "))

# Main
# Parâmetro lambda da distribuição Poisson
l = int(input("Insira um valor para lambda (λ): "))

# Valor máximo de X
m = int(input("Insira o valor máximo para x: "))

option = getOption()
while option != 0:
    if option == 1:
        makeProbGraph(l, (m + 1))
    elif option == 2:
        makeAccumGraph(l, (m + 1))
    elif option == 3:
        x = int(input("Insira o ponto: "))
        print("\nProbabilidade: {}".format(getY(l, x)))
    elif option == 4:
        print("Probabilidade Acumulada")
        print("1. Menor que (<)")
        print("2. Maior que (>)")
        print("3. Menor ou igual que (<=)")
        print("4. Maior ou igual que (>=)")
        print("5. Intervalo aberto, aberto ]x, y[")
        print("6. Intervalo aberto, fechado ]x, y]")
        print("7. Intervalo fechado, aberto [x, y[")
        print("8. Intervalo fechado, fechado [x, y]")
        print("0. Voltar")
        op = int(input("\nInsira a opção: "))
        getAccumProb(op, l)
    elif option == 5:
        l = int(input("Insira um novo valor para lambda (λ): "))
    elif option == 6:
        m = int(input("Insira o novo valor máximo para x: "))

    input()
    option = getOption()

print("\nDeveloped by:\n - Gabriel Luciano\n - Geovane Fonseca\n - Luigi Domenico\nBye :)")
