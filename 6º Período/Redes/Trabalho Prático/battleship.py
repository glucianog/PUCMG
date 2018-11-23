import random
import socket

ships  = [{'nome': 'Porta Aviao', 'size': 5, 'quantia': 1}, {'nome': 'Navio Tanque', 'size': 4, 'quantia': 2},
          {'nome': 'Contratorpedeiro', 'size': 3, 'quantia': 3}, {'nome': 'Submarino', 'size': 2, 'quantia': 4}]
          
orient = ['horizontal', 'vertical']

my_board       = []
my_shown_board = []
enemy_board    = []
shown_board    = []
enemy_counter  = 0
my_counter     = 0

# Mostrar um tabuleiro selecionado
def print_board(board):
  for row in board:
    print (" ".join(row))

# Enviar tabuleiro selecionado para o Cliente
def printBoardToClient(board, con):
    response = "\n"
    for row in board:
        response += (" ".join(row))   
        response += "\n" 
    con.send(str.encode(response))

def random_row():
  return random.randint(0,9)

def random_col():
  return random.randint(0,9)

# Verifica se uma determinada posição de um selecinado tabuleiro está disponível
def availablePos(board, i, j):    
    return board[i][j] == 'O' 
    
# Posiciona um navio dada uma determinada posição 
def setPosition(board, i, j, ori, ship):
    for k in range(ship['size']):
        if ori == 'esq':
            board[i][j-k] = str(ship['size'])
        elif ori == 'dir':
            board[i][j+k] = str(ship['size'])
        elif ori == 'sup':
            board[i-k][j] = str(ship['size'])
        else:
            board[i+k][j] = str(ship['size'])

"""
 Procura uma posição válida para inserção de um navio. Método utilizado
 pelo buildBoard(), uma vez que a frota é preenchida de forma aleatória
"""
def position_ship(board, ship, ori): 
    i = random_row()
    j = random_col()
    if availablePos(board, i, j):  # Posição escolhida está disponível?
        size = ship['size']
        aux = True
        if ori == 'horizontal':
            if (j + (size-1)) <= 9 : # O navio selecionado cabe à direita?
                for k in range(size):
                    if not availablePos(board, i, k+j):
                        k = size
                        aux = False
                if aux: # Se cabe, todas as posições à direita disponíveis
                    setPosition(board, i, j, 'dir', ship)
                else:   # Se direita não disponível, tentar esquerda
                    aux = True
                    for k in range(size):
                        if not availablePos(board, i, j-k):
                            k = size
                            aux = False
                    if aux: # Se cabe, todas as posições à esquerda disponíveis
                        setPosition(board, i, j, 'esq', ship)     
                    else: # Se não cabe à direita nem esquerda, procura outra posição
                        position_ship(board, ship, ori)
            elif (j - (size-1) >= 0): # Se cabe à direita, cabe à esquerda?
                aux = True
                for k in range(size):
                    if not availablePos(board, i, j-k):
                        k = size
                        aux = False
                if aux: 
                    setPosition(board, i, j, 'esq', ship)     
                else:
                    position_ship(board, ship, ori)
        elif ori == 'vertical':
            if (i + (size-1)) <= 9 : # O navio selecionado cabe abaixo?
                for k in range(size):
                    if not availablePos(board, i+k, j):
                        k = size
                        aux = False
                if aux: #Se cabe, todas as posições à inferior disponíveis
                    setPosition(board, i, j, 'inf', ship)
                else: # Se inferior não disponível, tentar superior
                    aux = True
                    for k in range(size):
                        if not availablePos(board, i-k, j):
                            k = size
                            aux = False
                    if aux: #Se cabe, todas as posições à superior disponíveis
                        setPosition(board, i, j, 'sup', ship)     
                    else: # Se não cabe à inferior nem superior, procura outra posição
                        position_ship(board, ship, ori)
            elif i - (i-1) >= 0: # Se não cabe à inferior, cabe à superior?
                aux = True
                for k in range(size):
                    if not availablePos(board, i-k, j):
                        k = size
                        aux = False
                if aux:
                    setPosition(board, i, j, 'sup', ship)     
                else:
                    position_ship(board, ship, ori)     
    else : # Se posição não está disponível, procurar outra.
        position_ship(board, ship, ori)

# Posicionamento de frota Aleatória
def buildBoard(board):
    global ships
    for ship in ships:
        for _ in range(ship['quantia']):
            ori = random.choice(orient)
            position_ship(board, ship, ori)

# Posicionar Minha frota
def buildMyBoard(con):
    global ships, my_board
    for ship in ships:
        for _ in range(ship['quantia']):
            while True: 
                response = "Linha  a posicionar o " + ship['nome']   + "\n"
                con.send(str.encode(response))                
                msg = con.recv(1024)
                i   = int(msg.decode())

                response = "Coluna a posicionar o " + ship['nome']   + "\n"
                con.send(str.encode(response))                
                msg = con.recv(1024)
                j   = int(msg.decode())

                response = "Orietação (esq, dir, sup, inf) para posicionar o " + ship['nome']  + "\n"
                con.send(str.encode(response))                
                msg = con.recv(1024)
                ori = msg.decode()

                if availablePos(my_board, i, j):
                    setPosition(my_board, i, j, ori, ship)
                    break
                else:
                    response = "Posição escolhida já apresenta algum navio posicionado.\n"
                    con.send(str.encode(response))

def startGame(con):
    global enemy_board, shown_board, my_board, my_shown_board, my_counter, enemy_counter

    while True:
        player = 0  
        response = "Insira uma opção: A - Atacar, P - Mostrar Tabuleiro \n"  
        con.send(str.encode(response))  
        msg = con.recv(1024)
        command = msg.decode().upper()

        if command == "A":         
            while player % 2 == 0 :  
                response = "Sua vez! \n Linha a ser atacada ou P para Mostrar Tabuleiro \n"  
                con.send(str.encode(response))  
                msg = con.recv(1024)      
                if msg == "P":
                    printBoardToClient(shown_board, con)
                    continue
                else: 
                    i = int(msg.decode())

                    response = "Coluna a ser atacada ou P para Mostrar Tabuleiro \n"  
                    con.send(str.encode(response))  
                    msg = con.recv(1024)  
                    if msg == "P":
                        printBoardToClient(shown_board, con)
                    else: 
                        j = int(msg.decode())

                        if choosenPos(i, j, "myself"):
                            if shown_board[i][j] != "-":
                                response = "Você acertou uma peça na posição x: {} y: {}! Faça mais uma jogada.\n".format(i, j)  
                                con.send(str.encode(response)) 
                            else:
                                response = "Você errou! Na posição x:{}, y{}\n".format(i,j)  
                                con.send(str.encode(response))
                                player += 1                
                        else:
                            response = "Você já atacou essa posição! Posição x:{}, y:{}\n".format(i,j) 
                            con.send(str.encode(response))
            
            i  = random_row()
            j  = random_col()

            
            while player % 2 != 0:
                response = "Rodada do inimigo!\n"                 
                con.send(str.encode(response))

                if choosenPos(i, j, "enemy"):
                    if my_shown_board[i][j] == "-":
                        if i + 1 < 10:
                            i += 1
                        elif j + 1 < 10:
                            j += 1
                        elif j - 1 >= 0:
                            j -= 1
                        elif i - 1 >= 0:
                            i -= 1
                    else:
                        player += 1
                else:
                    player += 1

        elif command == "P":
            printBoardToClient(shown_board, con)

        if enemy_counter == 30 or my_counter == 30:
            break
    
    if enemy_counter == 30:
        response = "Que pena, você perdeu!\n" 
        con.send(str.encode(response))
    else:
        response = "Parabéns! Você ganhou!\n" 
        con.send(str.encode(response))

#Resetar os tabuleiros do jogo 
def resetBoards():
    global my_board, my_shown_board, enemy_board, shown_board

    my_board       = []
    my_shown_board = []
    enemy_board    = []
    shown_board    = []

# Alocação dos tabuleiros do jogo    
def initBoards():
    global my_board, my_shown_board, enemy_board, shown_board

    for _ in range(0,10):
        my_board.append(["O"] * 10)
        enemy_board.append(["O"] * 10)
        shown_board.append(["O"] * 10)
        my_shown_board.append(["O"] * 10)  

# Posição selecionada para ataque
def choosenPos(i, j, player):
    global enemy_counter, my_counter
    resp = True
    if player == "myself":
        if shown_board[i][j] == "O":                
            if enemy_board[i][j] != "O":
                shown_board[i][j] = "-"
                my_counter += 1 
            else:
                shown_board[i][j] = "X"
        else:
            resp = False
    else:
        if my_shown_board == "O":
            if my_board[i][j] != "O":
                my_shown_board[i][j] = "-"
                enemy_counter += 1
            else:
                my_shown_board[i][j] = "X"
        else:
            return False
    return resp 

if __name__ == "__main__":
    HOST = ''                                        # Endereco IP do Servidor
    PORT = input("Insira a porta de comunicação: ")  # Porta que o Servidor esta

    tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    orig = (HOST, int(PORT))
    tcp.bind(orig)
    tcp.listen(1)      

    while True:
        con, cliente = tcp.accept()
        print('Concetado por', cliente)

        resetBoards() # Resetar todos os tabuleiros existentes
        initBoards()  # Alocar todos os tabuleiros existentes
        buildBoard(enemy_board) # Posicionar frota do inimigo de forma aleatória
        while True:      

            #Posicionar minha frota
            buildMyBoard(my_board)
            #Iniciar o Jogo
            startGame(con)
            response = "Fim do jogo. Para finalizar conexão com servidor, digite 'exit' \n "  
            con.send(str.encode(response))  
            msg = con.recv(1024)      
            if ms.decode() == "exit": break         
            
        print ('Finalizando conexao do cliente', cliente)
        #con.close()
        #break