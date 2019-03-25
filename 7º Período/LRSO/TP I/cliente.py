import socket
from time import sleep

HOST = '192.168.15.3'     # Endereco IP do Servidor
PORT = 5000            # Porta que o Servidor esta

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
dest = (HOST, PORT)
tcp.connect(dest)
print ('Para sair use CTRL+X\n')
print ('Pressione Enter para continuar\n')

msg = input()

while msg != '\x18':

    while msg != 'OK':
        msg = tcp.recv(1024)
        msg = msg.decode('utf-8')
        print("Recebido: {}".format(msg))
        sleep(0.5)
        if msg == 'OK': break
    msg = input()
    tcp.send (str.encode(msg))
    msg = tcp.recv(1024)
    msg = msg.decode('utf-8')
    print("Recebido: {}".format(msg))
   

tcp.close()