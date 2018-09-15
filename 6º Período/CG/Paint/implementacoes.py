from math import sqrt

def dda(p1, p2, cor): 
    x, y = p1['x'], p1['y']
    dx = p2['x'] - x
    dy = p2['y'] - y
    linha = [p1]

    if abs(dx) > abs(dy) :
        passos = int(abs(dx))
    else:
        passos = int(abs(dy))

    if passos == 0:
        return linha

    xincr = dx/passos
    yincr = dy/passos
    
    for _ in range(passos):
        x += xincr
        y += yincr
        linha.append({'x': round(x), 'y': round(y)})

    return linha

def bresenhan(p1, p2, cor):
    x, y = p1['x'], p1['y']
    dx = int(p2['x'] - x)
    dy = int(p2['y'] - y)
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

def cohenSutherland(p1, p2, pini, pfim):
    aceito = False
    feito = False
    (xmax, ymax, xmin, ymin) = limites(p1, p2)
    (x1, y1, x2, y2) = (pini['x'], pini['y'], pfim['x'], pfim['y'])
    
    while not feito:
        cod1 = calculaCodigo(p1, p2, x1, y1)
        cod2 = calculaCodigo(p1, p2, x2, y2)
        if cod1 == 0 and cod2 == 0:
            aceito = True
            feito  = True
        elif cod1 & cod2 != 0:
            feito  = True
        else:
            if cod1 != 0:
                cfora = cod1
            else:
                cfora = cod2

            if cfora & 1 == 1: #se bit 0 está setado
                xint = xmin
                yint = y1 + (y2-y1) * (xmin-x1)/ (x2-x1)
            elif cfora & 2 == 2: #se bit 1 está setado
                xint = xmax
                yint = y1 + (y2-y1) * (xmax-x1)/(x2-x1)
            elif cfora & 4 == 4: #se bit 2 está setado
                yint = ymin
                xint = x1 + (x2-x1) * (ymin-y1)/(y2-y1)
            elif cfora & 8 == 8: #se bit 3 está setado
                yint = ymax
                xint = x1 + (x2-x1) * (ymax-y1)/(y2-y1)

            if cfora == cod1: #atualiza ponto incial da reta
                x1 = xint
                y1 = yint
            else:             #atualiza ponto final da reta
                x2 = xint
                y2 = yint
    if(aceito):
        return (round(x1), round(y1), round(x2), round(y2))
    else:
        return ()

def calculaCodigo(p1, p2, x, y):
    cod = 0
    (xmax, ymax, xmin, ymin) = limites(p1, p2)
    if x < xmin:
        cod += 1
    elif x > xmax:
        cod += 2

    if y < ymin:
        cod += 4
    elif y > ymax:
        cod += 8

    return cod

def limites(p1, p2):
    if p1['x'] > p2['x']:
        xmax = p1['x']
        xmin = p2['x']
    else:
        xmax = p2['x']
        xmin = p1['x']

    if p1['y'] > p2['y']:
        ymax = p1['y']
        ymin = p2['y']
    else:
        ymax = p2['y']
        ymin = p1['y']
    
    return(xmax, ymax, xmin, ymin)

def liangBarsky(p1, p2, pini, pfim):
    u1 = 0
    u2 = 1

    (x1, y1, x2, y2) = (pini['x'], pini['y'], pfim['x'], pfim['y'])
    (xmax, ymax, xmin, ymin) = limites(p1, p2)

    dx = x2 - x1
    dy = y2 - y1
    
    u1, u2, result = cliptest(-dx, x1 - xmin, u1, u2)
    if result: #fronteira esquerda
        u1, u2, result = cliptest(dx, xmax - x1, u1, u2)
        if result: #fronteira direita
            u1, u2, result = cliptest(-dy, y1 - ymin, u1, u2)
            if result: #fronteira inferior
                u1, u2, result = cliptest(dy, ymax - y1, u1, u2)
                if result: #fronteira superior
                    if u2 < 1:
                        x2 = x1 + (dx * u2) # x1 = valor inicial antes do recorte
                        y2 = y1 + (dy * u2) # y1 = valor inicial antes do recorte
                    if u1 > 0:
                        x1 = x1 + (dx * u1)
                        y1 = y1 + (dy * u1)
                    return (round(x1), round(y1), round(x2), round(y2))

    return ()

def cliptest(p, q, u1, u2):
    result = True
    if p < 0:
        r = q/p
        if r > u2:
            result = False #fora da janela
        elif r > u1:
            u1 = r
    elif p > 0:
        r = q/p
        if r < u1:
            result = False #fora da janela
        elif r < u2:
            u2 = r
    elif q < 0:
        result = False
    return (u1, u2, result) #False = fora da janela, True = dentro da janela



"""
OS MÉTODOS DE PREENCHIMENTO FORAM IMPLEMENTADOS NO ARQUIVO __main__.py,
POIS NECESSITAM ACESSAR MÉTODOS DA CALSSE QWIDGET, QUE SE LÁ SE ENCONTRA.
COM ISSO, OS CÓDIGOS PODEM SER ENCONTRADOS (EM FORMA ITERATIVA), A PARTIR 
DA LINHA 426 DESTE ARQUIVO. OS MÉTODOS FORAM DESENVOLVIDOS COM AUXÍLIO DO 
ALUNO LUIGI DOMENICO CECCHINI SOARES
"""
def boundary4(x, y, borda, nova):
    atual = obterCor(x, y)
    if atual != borda and atual != nova:
        atribuirCor(x, y, nova)
        boundary4(x+1, y,  borda, nova)
        boundary4(x-1, y, borda, nova)
        boundary4(x, y+1, borda, nova)
        boundary4(x, y-1, borda, nova)

def flood4(x, y, antiga, nova):
    atual = obterCor(x, y)
    if atual == antiga:
        atribuiCor(x, y, nova)
        flood4(x+1, y, antiga, nova)
        flood4(x-1, y, antiga, nova)
        flood4(x, y+1, antiga, nova)
        flood4(x, y-1, antiga, nova)