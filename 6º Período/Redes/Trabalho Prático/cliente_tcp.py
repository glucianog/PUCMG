import socket

HOST = input("Insira o endereco IP: ")            # Endereco IP do Servidor
PORT = input("Insira a porta de comunicacao: ")   # Porta que o Servidor esta

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
dest = (HOST, int(PORT))
tcp.connect(dest)

print ('Para sair use CTRL+X\n')
print("Pressione enter para continuar!")
msg = input()

while msg != '\x18':
    tcp.send (str.encode(msg))
    data = tcp.recv(1024)
    data = data.decode('utf-8')
    print("Recebido: {}".format(data))
    msg = input()
        
tcp.close()

