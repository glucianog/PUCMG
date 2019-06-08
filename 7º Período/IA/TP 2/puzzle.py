from copy import deepcopy

# Return the coodinate of the element
def get_pos(state, elem):
    return [( i, row.index(elem) )
            for i, row in enumerate(state)
            if elem in row ][0]

# Change the elements position
def make_move(state, x, y, i, j):
    child = deepcopy(state)
    child[x][y] = child[i][j]
    child[i][j] = ' '
    return child

# Create possibles moves of the state
def children(state):
    pos_empty = get_pos(state, ' ')
    x, y = pos_empty[0], pos_empty[1]

    states = []

    if x - 1 >= 0:
        states.append(make_move(state, x, y, x - 1, y))
    if x + 1 < 3:
        states.append(make_move(state, x, y, x + 1, y))
    if y - 1 >= 0:
        states.append(make_move(state, x, y, x, y - 1))
    if y + 1 < 3:
        states.append(make_move(state, x, y, x, y + 1))

    return states
