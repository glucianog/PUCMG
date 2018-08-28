from math import sqrt

def dda(p1, p2, cor): 
    x, y = p1['x'], p1['y']
    dx = p2['x'] - x
    dy = p2['y'] - y
    linha = [p1]

    if abs(dx) > abs(dy) :
        passos = abs(dx)
    else:
        passos = abs(dy)

    xincr = dx/passos
    yincr = dy/passos

    while(x < p2['x']):
        x += xincr
        y += yincr
        linha.append({'x': round(x), 'y': round(y)})

    return linha

def bresenhan(p1, p2, cor):
    x, y = p1['x'], p1['y']
    dx = p2['x'] - x
    dy = p2['y'] - y
    linha = [p1]

    if dx < 0 :
        dx = -dx; xincr = -1
    else: 
        xincr = 1

    if dy < 0 : 
        dy = -dy
        yincr = -1
    else: 
        yincr = 1
        
    if dx > dy : #primeiro caso
        p = 2*dy-dx
        const1 = 2*dy; const2 = 2*(dy-dx)
        for i in range(dx):
            x += xincr  #sempre atualiza x
            if p < 0 :
                p+= const1
            else:
                y += yincr
                p+= const2
            linha.append({'x': x, 'y': y})
    else: #segundo caso
        p = 2*dx-dy; const1 = 2*dx; const2 = 2*(dx-dy)
        for i in range(dy):
            y += yincr #sempre atualiza y
            if p < 0 :
                p += const1
            else:
                p += const2
                x += xincr            
            linha.append({'x': x, 'y': y})
    return linha

def bresenhanCircunferencia(centro, p1, cor):
    raio = round(sqrt((centro['x'] - p1['x'])**2  +
                  (centro['y'] - p1['y'])**2))
    x = 0
    y = raio
    p = 3 - 2 * raio
    circulo = plotaSimetricos(centro, x, y)
    while x < y:
        if p < 0:
            p += 4 * x + 6
        else:
            p += 4 * (x - y) + 10
            y -= 1
        x += 1
        circulo += plotaSimetricos(centro,x,y)
    return circulo

def plotaSimetricos(centro, x, y):
    pontos = []
    pontos.append({'x': centro['x'] + x, 'y': centro['y'] + y})
    pontos.append({'x': centro['x'] + x, 'y': centro['y'] - y})
    pontos.append({'x': centro['x'] - x, 'y': centro['y'] + y})
    pontos.append({'x': centro['x'] - x, 'y': centro['y'] - y})
    pontos.append({'x': centro['x'] + y, 'y': centro['y'] + x})
    pontos.append({'x': centro['x'] + y, 'y': centro['y'] - x})
    pontos.append({'x': centro['x'] - y, 'y': centro['y'] + x})
    pontos.append({'x': centro['x'] - y, 'y': centro['y'] - x})
    return pontos