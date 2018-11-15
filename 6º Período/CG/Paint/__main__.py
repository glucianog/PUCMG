
import sys, os
from PyQt5.QtWidgets import (QMainWindow, QAction, qApp, QApplication, QDesktopWidget, QPushButton, QLineEdit, 
                             QInputDialog)
from PyQt5.QtGui     import (QIcon, QPainter, QPen, QColor)
from PyQt5.QtCore    import Qt
from implementacoes  import (dda, bresenhan, bresenhanCircunferencia, cohenSutherland, liangBarsky, bezier)
from dialogs         import *
from math            import * 

class Example(QMainWindow):    
    def __init__(self):
        super().__init__()
        self.linhas_dda = []
        self.linhas_bsr = []
        self.circulos   = []
        self.ctrlCurva  = []
        self.comando    = '' 
        self.preenchimento = []
        self.recorteIni = None
        self.recorteFim = None
        self.nPtsControle = -1
        self.auxControle  = 0
        self.initUI()    
           
            
    def initUI(self):              
        self.resize(1024, 768)
        self.center()
        self.setWindowTitle('Paint v1.0')        
        self.setWindowIcon(QIcon('icones/main.png'))   

        ########### SEÇÃO DE CRIAÇÃO E DEFINIÇÃO DA TOOLBAR ###########
        self.toolbar = self.addToolBar('Desenhar Figuras')   

        drawAct = QAction(QIcon('icones/line_dda.png'), 'Desenhar Reta - DDA (Ctrl+D)', self.toolbar)
        drawAct.setShortcut('Ctrl+D')
        drawAct.triggered.connect(self.btnDDA)
        self.toolbar.addAction(drawAct)
    
        drawAct = QAction(QIcon('icones/line_bsr.png'), 'Desenhar Reta - Bresenhan (Ctrl+B)', self.toolbar)
        drawAct.setShortcut('Ctrl+B')
        drawAct.triggered.connect(self.btnBSR)  
        self.toolbar.addAction(drawAct)
        
        drawAct = QAction(QIcon('icones/circle.png'), 'Desenhar Circulo (Ctrl+C)', self.toolbar)
        drawAct.setShortcut('Ctrl+C')
        drawAct.triggered.connect(self.btnCirculo)  
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/cut.png'), 'Recorte -  Cohen-Sutherland (Ctrl+R)', self.toolbar)
        drawAct.setShortcut('Ctrl+R')
        drawAct.triggered.connect(self.btnRecorteCS)
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/cut.png'), 'Recorte -  Liang-Barsky (Ctrl+K)', self.toolbar)
        drawAct.setShortcut('Ctrl+K')
        drawAct.triggered.connect(self.btnRecorteLB)
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/paint.png'), 'Preenchimento Boundary-Fill (Ctrl+Y)', self.toolbar)
        drawAct.setShortcut('Ctrl+Y')
        drawAct.triggered.connect(self.btnPreenchimentoBoundary)
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/paint.png'), 'Preenchimento Flood-Fill (Ctrl+F)', self.toolbar)
        drawAct.setShortcut('Ctrl+F')
        drawAct.triggered.connect(self.btnPreenchimentoFlood)
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/bezier.png'), 'Curva Bézier (Ctrl+E)', self.toolbar)
        drawAct.setShortcut('Ctrl+E')
        drawAct.triggered.connect(self.showCurvaDialog)
        self.toolbar.addAction(drawAct)

        drawAct = QAction(QIcon('icones/clear.png'), 'Limpar Tela (Ctrl+L)', self.toolbar)
        drawAct.setShortcut('Ctrl+L')
        drawAct.triggered.connect(self.limparTela)
        self.toolbar.addAction(drawAct)

      

        ########### SEÇÃO DE CRIAÇÃO E DEFINIÇÃO DA MENUBAR ###########
        menubar  = self.menuBar()
        transfMenu = menubar.addMenu('&Transformações')
        traAction = transfMenu.addAction('Translação')  
        traAction.triggered.connect(self.translacaoDialog)

        escAction = transfMenu.addAction('Escala')          
        escAction.triggered.connect(self.escalaDialog)  

        rotAction = transfMenu.addAction('Rotação')
        rotAction.triggered.connect(self.rotacaoDialog)
        
        refMenu = transfMenu.addMenu('Reflexão')
        refX = refMenu.addAction('Reflexão em X')
        refX.triggered.connect(self.reflexaoX)

        refY = refMenu.addAction('Reflexão em Y')
        refY.triggered.connect(self.reflexaoY)

        refC = refMenu.addAction('Reflexão no Centro')
        refC.triggered.connect(self.reflexaoCentro)

        cisMenu = transfMenu.addMenu('Cisalhamento')   
        cisX = cisMenu.addAction('Cisalhamento em X')
        cisX.triggered.connect(self.showDialogCisalhamentoX)
        cisY = cisMenu.addAction('Cisalhamento em Y')
        cisY.triggered.connect(self.showDialogCisalhamentoY)

        self.show()     

    def mousePressEvent(self, event):        
        if event.button() == Qt.LeftButton :
            if self.comando == 'dda':
                p1 = {'x': event.pos().x(), 'y': event.pos().y()}
                self.linhas_dda.append([p1,p1])
                print("Comando: dda, Valor de x:{}, valor de y:{}".format(p1['x'],p1['y']))
            elif self.comando == 'bsr':            
                p1 = {'x': event.pos().x(), 'y': event.pos().y()}
                self.linhas_bsr.append([p1,p1])
                print("Comando: bsr, Valor de x:{}, valor de y:{}".format(p1['x'],p1['y']))
            elif self.comando == 'circ':                            
                p1 = {'x': event.pos().x(), 'y': event.pos().y()}
                self.circulos.append([p1,p1])
                print("Comando: circ, Valor de x:{}, valor de y:{}".format(p1['x'],p1['y']))   
            elif self.comando == 'recortecs':
                self.recorteIni = {'x': event.pos().x(), 'y': event.pos().y()}                
                print("Comando: recortecs, Valor de x:{}, valor de y:{}".format(self.recorteIni['x'], self.recorteIni['y']))   
            elif self.comando == 'recortelb':
                self.recorteIni = {'x': event.pos().x(), 'y': event.pos().y()}                
                print("Comando: recortelb, Valor de x:{}, valor de y:{}".format(self.recorteIni['x'], self.recorteIni['y']))   
            elif self.comando == 'preenchimentoBoundary':
                x, y = event.pos().x(), event.pos().y()
                p = {'x': x, 'y': y} 
                borda = QColor(Qt.black).rgb()
                nova  = QColor(Qt.blue).rgb()
                self.preenchimento += self.boundary4(p, borda, nova)            
            elif self.comando == 'preenchimentoFlood':
                x, y = event.pos().x(), event.pos().y()
                p = {'x': x, 'y': y}
                atual = self.obterCor(x, y)
                nova  = QColor(Qt.blue).rgb()
                self.preenchimento += self.flood4(p, atual, nova)  
            elif self.comando == 'curvaBezier':                
                x, y = event.pos().x(), event.pos().y()
                if self.auxControle < self.nPtsControle:
                    self.auxControle += 1
                    self.ctrlCurva.append(tuple((x,y)))
                print("comando: {}, Valor de x:{}, valor de y:{}, qt pontos Controle:{}, qt pontos Aux: {}"
                    .format(self.comando, x, y, self.nPtsControle, self.auxControle))

    def mouseMoveEvent(self, event):
        if self.comando == 'dda':
            p2 = {'x': event.pos().x(), 'y': event.pos().y()}
            self.linhas_dda[len(self.linhas_dda) - 1][1] = p2
            self.update()
        if self.comando == 'bsr':
            p2 = {'x': event.pos().x(), 'y': event.pos().y()}
            self.linhas_bsr[len(self.linhas_bsr) - 1][1] = p2
            self.update()
        if self.comando == 'circ':
            p2 = {'x': event.pos().x(), 'y': event.pos().y()}
            self.circulos[len(self.circulos) - 1][1] = p2
            self.update()
        if self.comando == 'recortecs':
            self.recorteFim = {'x': event.pos().x(), 'y': event.pos().y()}
        if self.comando == 'recortelb':
            self.recorteFim = {'x': event.pos().x(), 'y': event.pos().y()}

    def paintEvent(self, e):
        cor = Qt.black    
        pen = QPen(cor, 3, Qt.SolidLine)                    
        painter = QPainter(self)
        painter.setPen(pen)    

        if self.comando == 'recortecs' and self.recorteIni:
            if self.recorteIni and self.recorteFim:
                pen = QPen(cor, 3, Qt.DashLine)
                painter.setPen(pen)
                painter.drawLine(self.recorteIni['x'], self.recorteIni['y'], self.recorteFim['x'], self.recorteIni['y']) #superior
                painter.drawLine(self.recorteIni['x'], self.recorteIni['y'], self.recorteIni['x'], self.recorteFim['y']) #esquerda
                painter.drawLine(self.recorteIni['x'], self.recorteFim['y'], self.recorteFim['x'], self.recorteFim['y']) #inferior
                painter.drawLine(self.recorteFim['x'], self.recorteFim['y'], self.recorteFim['x'], self.recorteIni['y']) #direita

                for pini, pfim in self.linhas_dda:
                    valores = cohenSutherland(self.recorteIni, self.recorteFim, pini, pfim)
                    if not valores:
                        continue
                    (x1, y1, x2, y2) = valores
                    p1 = {'x': x1, 'y': y1}
                    p2 = {'x': x2, 'y': y2}
                    for ponto in bresenhan(p1, p2, cor):
                        painter.drawPoint(ponto['x'], ponto['y'])

                for pini, pfim in self.linhas_bsr:
                    valores = cohenSutherland(self.recorteIni, self.recorteFim, pini, pfim)
                    if not valores:
                        continue
                    (x1, y1, x2, y2) = valores
                    p1 = {'x': x1, 'y': y1}
                    p2 = {'x': x2, 'y': y2}
                    for ponto in bresenhan(p1, p2, cor):
                        painter.drawPoint(ponto['x'], ponto['y'])

                # CURVA DE BÉZIER
                pen = QPen(Qt.red, 10, Qt.SolidLine)
                painter.setPen(pen)   
                for p in self.ctrlCurva:
                    painter.drawPoint(p[0], p[1])

                if self.nPtsControle == self.auxControle:                             
                        pen = QPen(Qt.blue, 3, Qt.SolidLine)
                        painter.setPen(pen) 
                        aux = bezier(500, self.ctrlCurva)

                        for pontos in aux:
                            painter.drawPoint(pontos['x'], pontos['y'])

            self.update()            
        elif self.comando == 'recortelb' and self.recorteIni:
            if self.recorteIni and self.recorteFim:
                pen = QPen(cor, 3, Qt.DashLine)
                painter.setPen(pen)
                painter.drawLine(self.recorteIni['x'], self.recorteIni['y'], self.recorteFim['x'], self.recorteIni['y']) #superior
                painter.drawLine(self.recorteIni['x'], self.recorteIni['y'], self.recorteIni['x'], self.recorteFim['y']) #esquerda
                painter.drawLine(self.recorteIni['x'], self.recorteFim['y'], self.recorteFim['x'], self.recorteFim['y']) #inferior
                painter.drawLine(self.recorteFim['x'], self.recorteFim['y'], self.recorteFim['x'], self.recorteIni['y']) #direita
                for pini, pfim in self.linhas_dda:
                    valores = liangBarsky(self.recorteIni, self.recorteFim, pini, pfim)
                    if not valores:
                        continue
                    (x1, y1, x2, y2) = valores
                    p1 = {'x': x1, 'y': y1}
                    p2 = {'x': x2, 'y': y2}
                    for ponto in bresenhan(p1, p2, cor):
                        painter.drawPoint(ponto['x'], ponto['y'])

                for pini, pfim in self.linhas_bsr:
                    valores = liangBarsky(self.recorteIni, self.recorteFim, pini, pfim)
                    if not valores:
                        continue
                    (x1, y1, x2, y2) = valores
                    p1 = {'x': x1, 'y': y1}
                    p2 = {'x': x2, 'y': y2}
                    for ponto in bresenhan(p1, p2, cor):
                        painter.drawPoint(ponto['x'], ponto['y'])

                # CURVA DE BÉZIER
                pen = QPen(Qt.red, 10, Qt.SolidLine)
                painter.setPen(pen)   
                for p in self.ctrlCurva:
                    painter.drawPoint(p[0], p[1])

                if self.nPtsControle == self.auxControle:                             
                        pen = QPen(Qt.blue, 3, Qt.SolidLine)
                        painter.setPen(pen) 
                        aux = bezier(500, self.ctrlCurva)

                        for pontos in aux:
                            painter.drawPoint(pontos['x'], pontos['y'])

            self.update()
        else:
            for p1,p2 in self.linhas_dda:
                for ponto in dda(p1,p2,cor):
                    painter.drawPoint(ponto['x'],ponto['y'])
            
            for p1,p2 in self.linhas_bsr:
                for ponto in bresenhan(p1,p2,cor):
                    painter.drawPoint(ponto['x'],ponto['y'])

            for centro, p in self.circulos:
                for ponto in bresenhanCircunferencia(centro, p, cor):
                    painter.drawPoint(ponto['x'],ponto['y'])

            ## PREENCHIMENTO DE FIGURAS
            pen = QPen(Qt.blue, 3, Qt.SolidLine)
            painter.setPen(pen) 
            for ponto in self.preenchimento:                    
                painter.drawPoint(ponto['x'], ponto['y'])

            # CURVA DE BÉZIER
            pen = QPen(Qt.red, 10, Qt.SolidLine)
            painter.setPen(pen)   
            for p in self.ctrlCurva:
                painter.drawPoint(p[0], p[1])

            if self.nPtsControle == self.auxControle:                             
                    pen = QPen(Qt.black, 3, Qt.SolidLine)
                    painter.setPen(pen) 
                    aux = bezier(10000, self.ctrlCurva)

                    for pontos in aux:
                        painter.drawPoint(pontos['x'], pontos['y'])

            self.update()

    def btnDDA(self):
        self.comando = 'dda'
    
    def btnBSR(self):
        self.comando = 'bsr'
    
    def btnCirculo(self):
        self.comando = 'circ'

    def btnRecorteCS(self):
        self.comando = 'recortecs'

    def btnRecorteLB(self):
        self.comando = 'recortelb'

    def btnPreenchimentoBoundary(self):
        self.comando = 'preenchimentoBoundary'
        
    def btnPreenchimentoFlood(self):
        self.comando = 'preenchimentoFlood'  
    
    def limparTela(self):
        self.circulos      = []
        self.linhas_bsr    = []
        self.linhas_dda    = []
        self.preenchimento = []
        self.ctrlCurva     = []
        self.auxControle   = 0
        self.nPtsControle  = 0
        self.update()

    def center(self):
        frame = self.frameGeometry()
        cpoint = QDesktopWidget().availableGeometry().center()
        frame.moveCenter(cpoint)
        self.move(frame.topLeft())

    ########### SEÇÃO DAS TRANSFORMAÇÕES ########### 
    def translacaoDialog(self):
        x, y, ok = TranslacaoDialog.getResults()
        if ok:                
            for dda in self.linhas_dda:
                for ponto in dda:
                    ponto['x'] += int(x)
                    ponto['y'] += int(y)
            for bsr in self.linhas_bsr:
                for ponto in bsr:
                    ponto['x'] += int(x)
                    ponto['y'] += int(y)
            for bsrCirculo in self.circulos:
                for ponto in bsrCirculo:
                    ponto['x'] += int(x)
                    ponto['y'] += int(y)

    def escalaDialog(self):
        a, b, ok = EscalaDialog.getResults()       
        if ok:  
            a = float(a.replace(',','.'))
            b = float(b.replace(',','.'))           
            for dda in self.linhas_dda:
                ponto_inicial = dda[0]
                for ponto in dda:
                    ponto['x'] = ((ponto['x'] - ponto_inicial['x'])*a) + ponto_inicial['x']
                    ponto['y'] = ((ponto['y'] - ponto_inicial['y'])*a) + ponto_inicial['y']
            for bsr in self.linhas_bsr:
                ponto_inicial = bsr[0]
                for ponto in bsr:
                    ponto['x'] = ((ponto['x'] - ponto_inicial['x'])*a) + ponto_inicial['x']
                    ponto['y'] = ((ponto['y'] - ponto_inicial['y'])*a) + ponto_inicial['y']
            for bsrCirculo in self.circulos:
                ponto_inicial = bsrCirculo[0]
                for ponto in bsrCirculo:
                    ponto['x'] = ((ponto['x'] - ponto_inicial['x'])*a) + ponto_inicial['x']
                    ponto['y'] = ((ponto['y'] - ponto_inicial['y'])*a) + ponto_inicial['y']

    def rotacaoDialog(self):
        angulo, ok = RotacaoDialog.getResults()
        if ok:
            duas_decimais = '{0:.2f}'
            sen_angulo = float(duas_decimais.format(sin(angulo)))
            cos_angulo = float(duas_decimais.format(cos(angulo)))
            for dda in self.linhas_dda:
                ponto_inicial = dda[0]
                for ponto in dda:
                    x1 = ((ponto['x'] - ponto_inicial['x']) * cos_angulo)
                    y1 = ((ponto['y'] - ponto_inicial['y']) * sen_angulo)

                    x2 = ((ponto['x'] - ponto_inicial['x']) * sen_angulo)
                    y2 = ((ponto['y'] - ponto_inicial['y']) * cos_angulo)

                    ponto['x'] = x1 - y1 + ponto_inicial['x']
                    ponto['y'] = x2 + y2 + ponto_inicial['y']

            for bsr in self.linhas_bsr:
                ponto_inicial = bsr[0]
                for ponto in bsr:
                    x1 = ((ponto['x'] - ponto_inicial['x']) * cos_angulo)
                    y1 = ((ponto['y'] - ponto_inicial['y']) * sen_angulo)

                    x2 = ((ponto['x'] - ponto_inicial['x']) * sen_angulo)
                    y2 = ((ponto['y'] - ponto_inicial['y']) * cos_angulo)
                    
                    ponto['x'] = x1 - y1 + ponto_inicial['x']
                    ponto['y'] = x2 + y2 + ponto_inicial['y']

    def reflexao(self, rx, ry):
        for dda in self.linhas_dda:
            ponto_inicial = dda[0]
            for ponto in dda:
                ponto['x'] = ((ponto['x'] - ponto_inicial['x']) * (rx)) + ponto_inicial['x']
                ponto['y'] = ((ponto['y'] - ponto_inicial['y']) * (ry)) + ponto_inicial['y']

        for bsr in self.linhas_bsr:
            ponto_inicial = bsr[0]
            for ponto in bsr:
                ponto['x'] = ((ponto['x'] - ponto_inicial['x']) * (rx)) + ponto_inicial['x']
                ponto['y'] = ((ponto['y'] - ponto_inicial['y']) * (ry)) + ponto_inicial['y']

        for bsrCirculo in self.circulos:
            ponto_inicial = bsrCirculo[0]
            for ponto in bsrCirculo:
                ponto['x'] = ((ponto['x'] - ponto_inicial['x']) * (rx)) + ponto_inicial['x']
                ponto['y'] = ((ponto['y'] - ponto_inicial['y']) * (ry)) + ponto_inicial['y']
        self.update()

    def reflexaoX(self):
        self.reflexao(1,-1)

    def reflexaoY(self):
        self.reflexao(-1,1)

    def reflexaoCentro(self):
        self.reflexao(-1,-1)

    def cisalhamento(self, forca, tag):
        if tag == 'x':
            x = 0
            y = forca
        elif tag == 'y':
            x = forca
            y = 0

        for dda in self.linhas_dda:
            ponto_inicial = dda[0]
            for ponto in dda:
                x1 = ((ponto['x'] - ponto_inicial['x']))
                y1 = ((ponto['y'] - ponto_inicial['y']) * y)

                x2 = ((ponto['x'] - ponto_inicial['x']) * x)
                y2 = ((ponto['y'] - ponto_inicial['y']))

                ponto['x'] = x1 + y1 + ponto_inicial['x']
                ponto['y'] = x2 + y2 + ponto_inicial['y']

        for bsr in self.linhas_bsr:
            ponto_inicial = dda[0]
            for ponto in bsr:
                x1 = ((ponto['x'] - ponto_inicial['x']))
                y1 = ((ponto['y'] - ponto_inicial['y']) * y)

                x2 = ((ponto['x'] - ponto_inicial['x']) * x)
                y2 = ((ponto['y'] - ponto_inicial['y']))

                ponto['x'] = x1 + y1 + ponto_inicial['x']
                ponto['y'] = x2 + y2 + ponto_inicial['y']
            
        for bsrCirculos in self.circulos:
            ponto_inicial = bsrCirculos[0]
            for ponto in bsrCirculos:
                x1 = ((ponto['x'] - ponto_inicial['x']))
                y1 = ((ponto['y'] - ponto_inicial['y']) * y)

                x2 = ((ponto['x'] - ponto_inicial['x']) * x)
                y2 = ((ponto['y'] - ponto_inicial['y']))

                ponto['x'] = x1 + y1 + ponto_inicial['x']
                ponto['y'] = x2 + y2 + ponto_inicial['y']

    def showDialogCisalhamentoX(self):
        text, ok = QInputDialog.getText(self, 'Cisalhamento em X',
                    'Insira o valor desejado')
        if ok:
            self.cisalhamento(float(text), 'x')
        
    def showDialogCisalhamentoY(self):
        text, ok = QInputDialog.getText(self, 'Cisalhamento em Y',
                    'Insira o valor desejado')
        if ok:
            self.cisalhamento(float(text), 'y')
        

    ########### SEÇÃO DO PREENCHIMENTO ##########
    def boundary4(self, ponto, borda, nova):
        stack = [ponto]
        points = []
        helper = {}

        while stack:
            p = stack.pop()
            x, y = p.values()
            key = '{},{}'.format(x, y)

            if key in helper:
                atual = helper[key]
            else:
                atual = self.obterCor(x, y)
                helper[key] = atual

            if atual != borda and atual != nova:
                helper[key] = nova
                points.append(p)

                stack.append({'x': x, 'y': y - 1})
                stack.append({'x': x, 'y': y + 1})
                stack.append({'x': x - 1, 'y': y})
                stack.append({'x': x + 1, 'y': y})      

        return points

    def flood4(self, ponto, antiga, nova):
        stack = [ponto]
        points = []
        helper = {}

        while stack:
            p = stack.pop()
            x, y = p.values()
            key = '{},{}'.format(x, y)

            if key in helper:
                atual = helper[key]
            else:
                atual = self.obterCor(x, y)
                helper[key] = atual

            if atual == antiga:
                helper[key] = nova
                points.append(p)

                stack.append({'x': x, 'y': y - 1})
                stack.append({'x': x, 'y': y + 1})
                stack.append({'x': x - 1, 'y': y})
                stack.append({'x': x + 1, 'y': y})      

        return points

    def obterCor(self, x, y):
        pixmap = self.grab()
        image = pixmap.toImage()
        cor = image.pixelColor(x, y).rgb()
        return cor  

    ########### SEÇÃO CURVA BÉZIER ##########
    def showCurvaDialog(self):
        self.comando = 'curvaBezier'
        x, ok = CurvaDialog.getResults()
        if ok:                
            self.nPtsControle = int(x)
            self.auxControle = 0
            self.ctrlCurva = []

    
if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())