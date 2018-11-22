import socket

HOST = ''                                        # Endereco IP do Servidor
PORT = input("Insira a porta de comunicacaoo: ")  # Porta que o Servidor esta

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
orig = (HOST, int(PORT))
tcp.bind(orig)
tcp.listen(1)

while True:
    con, cliente = tcp.accept()
    print('Concetado por', cliente)

    while True:
        msg = con.recv(1024)
        if not msg: break
        print(cliente, msg)
        response = input("Reply: ")
        con.send(str.encode(response))
        
    print ('Finalizando conexao do cliente', cliente)
    con.close()