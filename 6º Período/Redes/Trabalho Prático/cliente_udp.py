import socket
import random


def idRand():
    return random.randint(0,999)

HOST = input("Insira o endereço IP: ")            # Endereco IP do Servidor
PORT = input("Insira a porta de comunicação: ")   # Porta que o Servidor esta

udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
dest = (HOST, int(PORT))
print ('Para sair use CTRL+X\n')
print ('Pressione Enter para continuar')

msg = input()



while msg != '\x18':
    if msg == "":
        pass
    else:
        idMes = msg.split( )
        idMes = idMes[1]
        udp.sendto(str.encode(msg), dest)
        data = udp.recv(1024)
        print("Recebido: {}".format(data.decode()))
        if data.decode() != idMes:
            udp.sendto(str.encode(msg), dest) #reenviar informação          
    msg = input() 
udp.close()

 