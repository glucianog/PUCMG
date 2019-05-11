# -*- coding: utf-8 -*-
import _thread
import queue
import random
import re
import socket
from time import sleep
import threading


""" VARIABLES USED TO OPEN CONNECTION"""
HOST = ''              # Endereco IP do Servidor
PORT = 5000            # Porta que o Servidor esta

""" GLOBAL VARIABLES """
participantes = []
jogadores = []
jogadorAtual = None
start = False
fim = False
lider = None
tentativas = 0
palavraDoJogo = ""
palavraOculta = ""
letrasUtilizadas = ""
threadLock = threading.Lock()

""" FUNCTIONS """
def conectado(con, cliente):
    """Controle de threads conectadas ao servidor """
    global start
    global palavraDoJogo
    global tentativas
    print ('Novo jogador:', cliente)

    while True:                         
        msg = con.recv(1024)
        if msg and con == lider['socket']:
            msg = msg.decode()
            if len(participantes) >= 3 and not start:
                if tentativas == 0: 
                        tentativas = int(msg)
                        lider['socket'].send(str.encode("OK")) 
                        print("Número de Tentativas: {}".format(tentativas))
                elif palavraDoJogo == "":
                    palavraDoJogo = msg
                    sendToAll("Configurações realizadas. Iniciando o jogo.")   
                    print("Palavra definida: {}".format(palavraDoJogo))
                    gerarPalavraSecreta()
                    start = True      
                    lider['socket'].send(str.encode("Aguarde até que a partida seja finalizada." \
                                         " Você será notificado das ações ocorrentes no jogo!")) 
        elif start == True and jogadorAtual['socket'] == jogadores[0]['socket']:
            msg = msg.decode()
            l = list(msg)
            jogada(l[0])            
            threadLock.release()
        elif start == True and con == jogadorAtual['socket'] == jogadores[1]['socket']:
            msg = msg.decode()
            l = list(msg)
            jogada(l[0])            
            threadLock.release()
       
    print ('Sessão com jogador: {}, finalizada!'.format(cliente))
    con.close()
    _thread.exit()


def escolherLider():
    """ Define dentre os participantes o lider do jogo"""
    global lider
    lider = random.choice(participantes)


def fimDoJogo():
    """ Finalização devida do jogo"""
    global fim
    fim = True
    if palavraDoJogo == palavraOculta:
        sendToAll("****** Fim do jogo! ****** \n O Jogador {} é o vencedor! A palavra era: {}".\
            format(jogadorAtual['porta'],palavraDoJogo))        
    else:
        sendToAll("****** Fim do jogo! ****** \nNinguém adivinhou a palavra, que era: {}".format(palavraDoJogo))

    


def gerarPalavraSecreta():
    """ Gera uma palavra formada por * do tamanho da palavra a ser descoberta"""
    global palavraOculta
    for _ in range(len(palavraDoJogo)):
        palavraOculta += '*'
    print(palavraOculta)


def jogada(a):
    """ Verifica se a letra inserida pelo jogador faz parte da palavra a ser descoberta. Se
        não fizer parte, altera entre os jogadores. Além disso verifica se o jogo chegou ao
        fim. 
    """
    global letrasUtilizadas
    if a in palavraDoJogo:
        replaceLetra(a)
    else:
        letrasUtilizadas +=  a + " "
        vezDoJogador()
    
    if palavraDoJogo == palavraOculta or \
            len(letrasUtilizadas) == tentativas:
        fimDoJogo()


def preencherJogadores():
    """ Preenche uma lista de jogadores sem o lider"""
    global jogadores
    for x in participantes:
        if x['porta'] != lider['porta']:
            jogadores.append(x)


def replaceLetra(a):
  """ Substitui a palavra formada por * com a letra encontrada""" 
  global palavraOculta
  palavraSecreta = list(palavraOculta)

  for m in re.finditer(a, palavraDoJogo):
    palavraSecreta[m.start()] = a

  palavraOculta = "".join(palavraSecreta)


def sendToAll(a):
    """ Envia uma determinada mensagem #a para todos os conectados (incluindo lider)."""
    for x in participantes:
        con = x['socket']
        con.send(str.encode(a))  


def startJogo():
    """ Inicio do jogo propriamente dito """
    global jogadorAtual
    escolherLider()
    for x in participantes:
        con = x['socket']
        con.send(str.encode("O JOGO ESTÁ PRESTES A COMEÇAR! \n Você é o jogador:{}".format(x['porta'])))
        sleep(2)
        con.send(str.encode("\n O LIDER É: {}".format(lider['porta'])))

    lider['socket'].send(str.encode("Insira o número de tantativas"))
    sleep(0.5)
    lider['socket'].send(str.encode("OK"))   

    sleep(0.5)
    lider['socket'].send(str.encode("Insira a palavra desejada"))

    while 1:
        if start == True: break

    preencherJogadores()
    vezDoJogador()    
    while fim == False:
        threadLock.acquire() 
        if fim: break       
        jogadorAtual['socket'].send(str.encode("Sua vez. Digite uma letra!"))
        statusJogo()
        sleep(1)
        jogadorAtual['socket'].send(str.encode("OK"))
       
 
def statusJogo():
    """ Envia para todos os jogadores informações atuais do jogo. """
    sendToAll("\nPalavra: {} \nLetras erradas: {}".\
            format(palavraOculta, letrasUtilizadas))


def vezDoJogador():
    """ Define um jogador inicial e alterna entre os jogadores caso algum deles
        tenha errado uma letra. 
    """
    global jogadorAtual
    if jogadorAtual == None:
        jogadorAtual = random.choice(jogadores)
    elif jogadorAtual == jogadores[0]:
        jogadorAtual = jogadores[1]
    else:
        jogadorAtual = jogadores[0]


""" CODE START """
if __name__ == "__main__":
    tcp  = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    orig = (HOST, PORT)

    tcp.bind(orig)
    tcp.listen(1)
    qtMinParticipantes = int(input("Quantos jogadores irão participar do jogo? "))
    while (qtMinParticipantes < 3):
        print("Ao menos três jogadores são necessários para iniciar o jogo.")
        qtMinParticipantes = input("Quantos jogadores irão participar do jogo? ")
    print("*************** Alguardando Conexão dos jogadores! ***************")

    while True:
        con, cliente = tcp.accept()
        _thread.start_new_thread(conectado, tuple([con, cliente]))
        participantes.append({'socket':con, 'porta':cliente})
        if len(participantes) == qtMinParticipantes:
            startJogo()
        if fim: break
    tcp.close()

