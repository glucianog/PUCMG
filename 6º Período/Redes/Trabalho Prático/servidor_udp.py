import socket

HOST = ''                                        # Endereco IP do Servidor
PORT = input("Insira a porta de comunicação: ")  # Porta que o Servidor esta

udp = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
orig = (HOST, int(PORT))
udp.bind(orig)

while True:

    msg, cliente = udp.recvfrom(1024)
    if not msg: break
    print(cliente, msg)    
    
    comando = msg.decode().split( )
    response = comando[1]
    preco = 999999

    if comando[0] == 'P':
        alcanceLat = (int(comando[4]) - int(comando[3]), int(comando[4]) + int(comando[3]))
        alcanceLon = (int(comando[5]) - int(comando[3]), int(comando[5]) + int(comando[3]))
        with open("precos.in") as f:
            for line in f: 
                lMsg = line.split( )
                if (int(lMsg[2]) >= alcanceLat[0] and int(lMsg[2]) <= alcanceLat[1] and
                    int(lMsg[3]) >= alcanceLon[0] and int(lMsg[3]) <= alcanceLon[1] and lMsg[0] == comando[2]):
                    if int(lMsg[1]) < preco:
                        preco = int(lMsg[1])
        if preco != 999999:
            udp.sendto(str.encode("Menor preço encontrado: " + str(preco)), cliente) 

    if comando[0] == 'D':
        fh = open("precos.in", "a")
        for k in range(2, len(comando)):
            fh.write(comando[k] + " ")
        fh.write("\n")
        fh.close()

    if comando[0] == 'print':
        fh =  open("precos.in", "r")
        for line in fh: 
            print(line)
            
    udp.sendto(str.encode(response), cliente)    

udp.close()