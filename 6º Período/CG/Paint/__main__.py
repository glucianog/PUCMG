
import sys, os
from PyQt5.QtWidgets import (QMainWindow, QAction, qApp, QApplication, QDesktopWidget, QPushButton, QLineEdit, 
                             QInputDialog)
from PyQt5.QtGui     import (QIcon, QPainter, QPen)
from PyQt5.QtCore    import Qt
from implementacoes  import (dda, bresenhan, bresenhanCircunferencia)

class Example(QMainWindow):    
    def __init__(self):
        super().__init__()
        self.linhas_dda = []
        self.linhas_bsr = []
        self.circulos   = []
        self.comando    = '' 
        self.initUI()       
            
    def initUI(self):              
        self.resize(1024, 768)
        self.center()
        self.setWindowTitle('Paint v1.0')        
        self.setWindowIcon(QIcon('icones/main.png'))   

        ########### SEÇÃO DE CRIAÇÃO E DEFINIÇÃO DA TOOLBAR ###########
        self.toolbar = self.addToolBar('Desenhar Figuras')  

        # se você criar um QActionGroup e setar um atributo lá como 'exclusive' (já é o padrão)
        # os actions que voce colocar como parentes vão ser "selecionados" apenas 1 por vez (não precisa daquela minha função pra isso)    

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

        drawAct = QAction(QIcon('icones/clear.png'), 'Limpar Tela (Ctrl+L)', self.toolbar)
        drawAct.setShortcut('Ctrl+L')
        drawAct.triggered.connect(self.limparTela)
        self.toolbar.addAction(drawAct)

        ########### SEÇÃO DE CRIAÇÃO E DEFINIÇÃO DA MENUBAR ###########
        menubar  = self.menuBar()
        transfMenu = menubar.addMenu('&Transformações')
        traAction = transfMenu.addAction('Translação')  

        escAction = transfMenu.addAction('Escala')          
        escAction.triggered.connect(self.showDialogEscala)  

        rotAction = transfMenu.addAction('Rotação')
        rotAction.triggered.connect(self.showDialogRotacao)
        
        refMenu = transfMenu.addMenu('Reflexão')
        refX = refMenu.addAction('Reflexão em X')
        refY = refMenu.addAction('Reflexão em Y')
        refC = refMenu.addAction('Reflexão no Centro')

        cisMenu = transfMenu.addMenu('Cisalhamento')   
        cisX = cisMenu.addAction('Cisalhamento em X')
        cisX.triggered.connect(self.showDialogCisalhamentoX)
        cisY = cisMenu.addAction('Cisalhamento em Y')
        cisY.triggered.connect(self.showDialogCisalhamentoY)

        self.show()     

        self.le = QLineEdit(self)
        self.le.move(130, 22)
        

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

    def paintEvent(self, e):
        cor = Qt.black    
        pen = QPen(cor, 3, Qt.SolidLine)                    
        painter = QPainter(self)
        painter.setPen(pen)    

        for p1,p2 in self.linhas_dda:
            for ponto in dda(p1,p2,cor):
                painter.drawPoint(ponto['x'],ponto['y'])
        
        for p1,p2 in self.linhas_bsr:
            for ponto in bresenhan(p1,p2,cor):
                painter.drawPoint(ponto['x'],ponto['y'])

        for centro, p in self.circulos:
            for ponto in bresenhanCircunferencia(centro, p, cor):
                painter.drawPoint(ponto['x'],ponto['y'])

    def btnDDA(self):
        self.comando = 'dda'
    
    def btnBSR(self):
        self.comando = 'bsr'
    
    def btnCirculo(self):
        self.comando = 'circ'  
    
    def limparTela(self):
        self.circulos   = []
        self.linhas_bsr = []
        self.linhas_dda = []
        self.update()

    def showDialogEscala(self):
        text, ok = QInputDialog.getText(self, 'Escala',
            'Insira a medida desejada')
        if ok:
            self.le.setText(str(text))

    def showDialogRotacao(self):
        text, ok = QInputDialog.getText(self, 'Rotação',
            'Insira o grau para roatação')
        if ok:
            self.le.setText(str(text))

    def showDialogCisalhamentoX(self):
        text, ok = QInputDialog.getText(self, 'Cisalhamento em X',
                    'Insira o valor desejado')
        if ok:
            self.le.setText(str(text))

        
    def showDialogCisalhamentoY(self):
        text, ok = QInputDialog.getText(self, 'Cisalhamento em Y',
                    'Insira o valor desejado')
        if ok:
            self.le.setText(str(text))


        
    def center(self):
        frame = self.frameGeometry()
        cpoint = QDesktopWidget().availableGeometry().center()
        frame.moveCenter(cpoint)
        self.move(frame.topLeft())
        
if __name__ == '__main__':

    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())