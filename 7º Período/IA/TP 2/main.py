from algorithms import bfs, greedy, astar

# Print puzzle of the given board
def print_puzzle(label, board):
    print(label)
    for row in board:
        print(row)
    print('\n')

# Resolve the test to the solution with BFS, Greedy and Astar methods
def test(test, goal, label='Test'):

    print('\n' + label)
    print_puzzle('Objetivo:', goal)
    print_puzzle('Inicial:', test)

    print('ALGORITMO GULOSO - Qtde passos: ', greedy(test, goal))
    print('ALGORITMO A ESTRELA - Qtde passos: ', astar(test, goal))
    print('ALGORITMO BFS - Qtde passos: ', bfs(test, goal))

# Main method
if __name__ == '__main__':
    goal = [[' ', '1', '2'], ['3', '4', '5'], ['6', '7', '8']]

    test([[' ', '1', '2'], ['3', '4', '5'], ['6', '7', '8']], goal, 'TESTE - Solução')
    test([['1', '2', ' '], ['3', '4', '5'], ['6', '7', '8']], goal, 'TESTE - Bem Perto da Solução')
    test([['1', '4', '2'], ['3', '5', '8'], ['6', ' ', '7']], goal, 'TESTE - Perto da Solução')
    test([['5', '2', '8'], ['4', '1', '7'], [' ', '3', '6']], goal, 'TESTE - Embaralhado 1')
    test([['2', '3', '6'], [' ', '1', '8'], ['4', '5', '7']], goal, 'TESTE - Embaralhado 2')