import io
import plotly.offline as py
import plotly.graph_objs as go
import subprocess
import time

path = "c:/Users/gabri/Desktop/PUCMG/5º Período/PAA/TP 01/Testes/"
greedy_exe = path + "greedy.exe"
dp_exe = path + "dp.exe"


days = [i for i in range(1, 22)]
dishes = [i for i in range(1, 51)]
budget = [i for i in range(0, 101)]

max_days = days[len(days) - 1]
max_dishes = dishes[len(dishes) - 1]
max_budget = budget[len(budget) - 1]

greedy_time = [0 for i in range(len(days))]
dp_time = [0 for i in range(len(days))]

i = 0
# Variando quantidade de dias
for day in days:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(day, max_dishes, max_budget))
        start_time = time.time()
        subprocess.run([greedy_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        greedy_time[i] += (end_time - start_time);
    greedy_time[i] /= 20.0
    i += 1


i = 0
for day in days:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(day, max_dishes, max_budget))
        start_time = time.time()
        subprocess.run([dp_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        dp_time[i] += (end_time - start_time);
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=days,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=days,
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
    ),
    yaxis=dict(
        title="Tempo de execução (em segundos) ",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/days.html")

# Variando quantidade de pratos
greedy_time = [0 for i in range(len(dishes))]
dp_time = [0 for i in range(len(dishes))]

i = 0
for dish in dishes:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(max_days, dish, max_budget))
        start_time = time.time()
        subprocess.run([greedy_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        greedy_time[i] += (end_time - start_time);
    greedy_time[i] /= 20.0
    i += 1


i = 0
for dish in dishes:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(max_days, dish, max_budget))
        start_time = time.time()
        subprocess.run([dp_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        dp_time[i] += (end_time - start_time);
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=dishes,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=dishes,
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
    ),
    yaxis=dict(
        title="Tempo de execução (em segundos) ",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/dishes.html")


# Variando orçamento
greedy_time = [0 for i in range(len(budget))]
dp_time = [0 for i in range(len(budget))]

i = 0
for b in budget:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(max_days, max_dishes, b))
        start_time = time.time()
        subprocess.run([greedy_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        greedy_time[i] += (end_time - start_time);
    greedy_time[i] /= 20.0
    i += 1


i = 0
for b in budget:
    for j in range(20):
        input_file = open("inputs/{}_{}_{}.in".format(max_days, max_dishes, b))
        start_time = time.time()
        subprocess.run([dp_exe], stdin=input_file, stdout=subprocess.PIPE)
        end_time = time.time();
        dp_time[i] += (end_time - start_time);
    dp_time[i] /= 20.0
    i += 1

trace_greedy = go.Scatter(
    x=budget,
    y=greedy_time,
    name="Guloso"
)

trace_dp = go.Scatter(
    x=budget,
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
    ),
    yaxis=dict(
        title="Tempo de execução (em segundos) ",
         titlefont=dict(
            family="Arial, sans-serif",
            size=18,
        ),
    )
)
figure = go.Figure(data=data, layout=layout)
py.plot(figure, filename=path + "outputs/budget.html")