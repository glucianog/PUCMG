# -*- coding: utf-8 -*-
import socket
from time import sleep

HOST = '127.0.0.1'     # Endereco IP do Servidor
PORT = 5000            # Porta que o Servidor esta

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
dest = (HOST, PORT)
tcp.connect(dest)
print ('Para sair use CTRL+X\n')
print ('Pressione Enter para continuar\n')

fim = False
msg = input()

while msg != '\x18':

    while 'OK' not in msg:
        try:
            msg = tcp.recv(1024)
            msg = msg.decode('utf-8')
            print("Recebido: {}".format(msg))
            sleep(0.5)
        except Exception:
            fim = True
            break
    if fim: break
    msg = input()
    tcp.send (str.encode(msg))
    msg = tcp.recv(1024)
    msg = msg.decode('utf-8')
    print("Recebido: {}".format(msg))
    if "Fim" in msg: break   

tcp.close()