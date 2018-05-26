import io
import plotly.offline as py
import plotly.graph_objs as go
import subprocess
import time

path = "c:/Users/gabri/Desktop/PUCMG/5º Período/PAA/TP 01/"
greedy_exe = path + "greedy.exe"
dp_exe = path + "dp.exe"

dias = [3, 6, 9, 12, 15]
pratos = [2, 4, 6, 8, 10]
orcamento = [20, 40, 60, 80, 100]

greedy_time = [0 for i in range(5)]
dp_time = [0 for i in range(5)]

i = 0
# Variando quantidade de dias
for dia in dias:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dia, pratos[0], orcamento[0]))
        start_time = time.time()
        subprocess.run([greedy_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        greedy_time[i] += (end_time - start_time) / 5.0
    greedy_time[i] /= 20.0
    i += 1


i = 0
for dia in dias:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dia, pratos[0], orcamento[0]))
        start_time = time.time()
        subprocess.run([dp_exe], shell=True, stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        dp_time[i] += (end_time - start_time) / 5.0
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=dias,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=dias,
    y=dp_time,
    name="Dinâmico"
)

data = [trace_greedy, trace_dp]
layout = go.Layout(
    xaxis=dict(
        title="Dias",
        titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 16]
    ),
    yaxis=dict(
        title="Tempo de execução",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 0.015]
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/dias.html")

# Variando quantidade de pratos
greedy_time = [0 for i in range(5)]
dp_time = [0 for i in range(5)]

i = 0
for prato in pratos:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dias[0], prato, orcamento[0]))
        start_time = time.time()
        subprocess.run([greedy_exe], shell=True, stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        greedy_time[i] += (end_time - start_time) / 5.0
    greedy_time[i] /= 20.0
    i += 1


i = 0
for prato in pratos:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dias[0], prato, orcamento[0]))
        start_time = time.time()
        subprocess.run([dp_exe], shell=True, stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        dp_time[i] += (end_time - start_time) / 5.0
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=pratos,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=pratos,
    y=dp_time,
    name="Dinâmico"
)

data = [trace_greedy, trace_dp]
layout = go.Layout(
    xaxis=dict(
        title="Nº de pratos",
        titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 11]
    ),
    yaxis=dict(
        title="Tempo de execução",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 0.015]
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/pratos.html")


# Variando orcamento
greedy_time = [0 for i in range(5)]
dp_time = [0 for i in range(5)]

i = 0
for orc in orcamento:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dias[0], pratos[0], orc))
        start_time = time.time()
        subprocess.run([greedy_exe], shell=True, stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        greedy_time[i] += (end_time - start_time) / 5.0
    greedy_time[i] /= 20.0
    i += 1


i = 0
for orc in orcamento:
    for j in range(20):
        input_file = open("inputs/pub_{0}_{1}_{2}.in".format(dias[0], pratos[0], orc))
        start_time = time.time()
        subprocess.run([dp_exe], shell=True, stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time()
        dp_time[i] += (end_time - start_time) / 5.0
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=orcamento,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=orcamento,
    y=dp_time,
    name="Dinâmico"
)

data = [trace_greedy, trace_dp]
layout = go.Layout(
    xaxis=dict(
        title="Orçamento",
        titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 101]
    ),
    yaxis=dict(
        title="Tempo de execução",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
        range=[0, 0.015]
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/orcamento.html")
