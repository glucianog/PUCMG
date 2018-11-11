import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore    import *
from PyQt5.QtGui     import *
from math 			 import *
from locale 		 import *

class TranslacaoDialog(QDialog):
	def __init__(self, parent = None):
		super(TranslacaoDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Translação Eixo X
		self.trans_x = QLineEdit()
		self.trans_x.setValidator(QIntValidator())
		self.trans_x.setMaxLength(4)
		layout.addRow("Translação eixo X: ", self.trans_x)

		# Input Translação Eixo Y
		self.trans_y = QLineEdit()
		self.trans_y.setValidator(QIntValidator())
		self.trans_y.setMaxLength(4)
		layout.addRow("Translação eixo Y: ", self.trans_y)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getX(self):
		return self.trans_x.text()

	def getY(self):
		return self.trans_y.text()

	# Método estático que cria o dialog e retorna (trans_x, trans_y, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = TranslacaoDialog(parent)
		result = dialog.exec_()
		trans_x = dialog.getX()
		trans_y = dialog.getY()
		return (trans_x, trans_y, result == QDialog.Accepted)

class EscalaDialog(QDialog):
	def __init__(self, parent = None):
		super(EscalaDialog, self).__init__(parent)

		layout = QFormLayout(self)
		
		# Input Translação Eixo X
		self.escalaA = QDoubleSpinBox()
		self.escalaA.setMinimum(0)
		layout.addRow("Valor de a: ", self.escalaA)

		self.escalaB = QDoubleSpinBox()
		self.escalaB.setMinimum(0)
		layout.addRow("Valor de b: ", self.escalaB)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getEscalaA(self):
		return self.escalaA.text()

	def getEscalaB(self):
		return self.escalaB.text()
	# Método estático que cria o dialog e retorna (trans_x, trans_y, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = EscalaDialog(parent)
		result = dialog.exec_()
		escalaA = dialog.getEscalaA()
		escalaB = dialog.getEscalaB()
		return (escalaA, escalaB, result == QDialog.Accepted)


class RotacaoDialog(QDialog):
	def __init__(self, parent = None):
		super(RotacaoDialog, self).__init__(parent)

		layout = QFormLayout(self)

		self.angulo = QDoubleSpinBox()
		self.angulo.setRange(-360,360)
		layout.addRow("Ângulo: ", self.angulo)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
		QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
		Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getAngulo(self):
		return self.angulo.text()

	# Método estático que cria o dialog e retorna (trans_x, trans_y, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = RotacaoDialog(parent)
		result = dialog.exec_()
		angulo = dialog.getAngulo()
		return (radians(float(angulo.replace(',','.'))), result == QDialog.Accepted)


class CurvaDialog(QDialog):
	def __init__(self, parent = None):
		super(CurvaDialog, self).__init__(parent)

		layout = QFormLayout(self)

		# Input Quantidade Pontos Controle
		self.pControle = QLineEdit()
		self.pControle.setValidator(QIntValidator())
		self.pControle.setMaxLength(4)
		layout.addRow("Quantidade pontos controle: ", self.pControle)

		# Butões de OK e Cancel
		buttons = QDialogButtonBox(
			QDialogButtonBox.Ok | QDialogButtonBox.Cancel,
			Qt.Horizontal, self)
		buttons.accepted.connect(self.accept)
		buttons.rejected.connect(self.reject)
		layout.addRow(buttons)

	def getQtPontos(self):
		return self.pControle.text()

	# Método estático que cria o dialog e retorna (qtPontos, aceito)
	@staticmethod
	def getResults(parent = None):
		dialog = CurvaDialog(parent)
		result = dialog.exec_()
		qPontos = dialog.getQtPontos()
		return (qPontos, result == QDialog.Accepted)