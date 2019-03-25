import socket
import _thread
import random
import queue
from time import sleep

HOST = ''              # Endereco IP do Servidor
PORT = 5000            # Porta que o Servidor esta

jogadores = []
start = False
lider = None
tentativas = 0
palavraDoJogo = ""


def sendToAll():
    for x in jogadores:
        con = x['socket']
        con.send(str.encode("O JOGO ESTÁ PRESTES A COMEÇAR! Você é o jogador:{}".format(x['porta'])))
        sleep(2)
        con.send(str.encode("O LIDER É: {}".format(lider['porta'])))

def escolherLider():
    global lider
    lider = random.choice(jogadores)
    print(lider['porta'])
    
def startJogo():
    escolherLider()
    sendToAll()

    lider['socket'].send(str.encode("Insira o número de tantativas"))
    sleep(0.5)
    lider['socket'].send(str.encode("OK"))   

    sleep(0.5)
    lider['socket'].send(str.encode("Insira a palavra desejada"))
    sleep(0.5)
    lider['socket'].send(str.encode("OK"))




def conectado(con, cliente):
    global start
    global palavraDoJogo
    global tentativas
    print ('Novo jogador:', cliente)

    while True:
        if len(jogadores) >= 3 and start == False:
            if con == lider['socket']:
                msg = lider['socket'].recv(1024)
                msg = msg.decode()
                if tentativas == 0: 
                    tentativas = int(msg)
                    lider['socket'].send(str.encode("OK")) 
                    print("Número de Tentativas: {}".format(tentativas))                   
                else:
                    palavraDoJogo = msg.decode('utf-8')
                    lider['socket'].send(str.encode("OK"))   
                    print("Palavra definida: {}".format(palavraDoJogo))
                    start = True            
        msg = con.recv(1024)
        if not msg: break
        print("Mensagem Caiu aqui", cliente, msg)      

    print ('Sessão com jogador: {}, finalizada!'.format(cliente))
    con.close()
    _thread.exit()

if __name__ == "__main__":
    tcp  = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    orig = (HOST, PORT)

    tcp.bind(orig)
    tcp.listen(1)

    while True:
        con, cliente = tcp.accept()
        _thread.start_new_thread(conectado, tuple([con, cliente]))
        jogadores.append({'socket':con, 'porta':cliente})
        if len(jogadores) >= 3:
            startJogo()



    tcp.close()

