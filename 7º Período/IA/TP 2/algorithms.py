from puzzle import children, get_pos

# Queue is a list and state is an integer. The queue is used
# to get the fathers of the current element; the state is 
# the index of the solution on the path.
# Returns the number of moves made until reach final state.
def nmoves(queue, state):
    n = 0

    while queue[state]['path'] != -1:
        n += 1
        state = queue[state]['path']
    return n

# Manhattan Distance Method: Uses a distance from the increment with the
#   current value of the tray for calculating the distance
def manhattan(state, final):
    h = 0
    for i, row in enumerate(state):
        for j, elem in enumerate(row):
            x, y = get_pos(final, elem)
            h += abs(x - i) + abs(y - j)
    return h

# Breadth First Search (BFS) search method algorithm
# Start and final are list of lists. The first one represents the initial
# state; the last one, the final state.
# Returns the number of moves made until reach final state.
def bfs(start, final):
    queue, path, visited = [], [], []

    state = 0
    foundsolution = False

    elem = {
        'state': start,
        'path': -1
    }

    queue.append(elem)
    path.append(elem)

    while queue:
        current = queue.pop(0)
         
        if current['state'] == final:
            foundsolution = True
            break

        if current['state'] in visited:
            continue

        visited.append(current['state'])
        for child in children(current['state']):                
            if child not in visited:
                elem = {
                    'state': child,
                    'path': state
                }
                queue.append(elem)
                path.append(elem)

        state += 1

    return nmoves(path, state) if foundsolution else -1

# Greedy search method algorithm
# Start and final are list of lists. The first one represents the initial
# state; the last one, the final state.
# Returns the number of moves made until reach final state.
def greedy(start, final):
    queue, path, visited = [], [], []

    state = 0
    foundsolution = False

    elem = {
        'state': start,
        'path': -1,
        'h': manhattan(start, final)
    }

    queue.append(elem)
    path.append(elem)

    while queue:
        current = queue.pop(0)
         
        if current['state'] == final:
            foundsolution = True
            break

        if current['state'] in visited:
            continue

        visited.append(current['state'])
        for child in children(current['state']):                
            if child not in visited:
                elem = {
                    'state': child,
                    'path': state,
                    'h': manhattan(child, final)
                }
                queue.append(elem)
                path.append(elem)

        queue = sorted(queue, key=lambda k: k['h']) 
        state += 1

    return nmoves(path, state) if foundsolution else -1

# A Star search method algorithm
# Start and final are list of lists. The first one represents the initial
# state; the last one, the final state.
# Returns the number of moves made until reach final state.
def astar(start, final):
    queue, path, visited = [], [], []

    state = 0
    foundsolution = False

    elem = {
        'state': start,
        'path': -1,
        'g': 0,
        'h+g': manhattan(start, final) + 0
    }

    queue.append(elem)
    path.append(elem)

    while queue:
        current = queue.pop(0)
         
        if current['state'] == final:
            foundsolution = True
            break

        if current['state'] in visited:
            continue

        visited.append(current['state'])
        for child in children(current['state']):                
            if child not in visited:
                elem = {
                    'state': child,
                    'path': state,
                    'g': current['g'] + 1,
                    'h+g': manhattan(child, final) + current['g'] + 1
                }
                queue.append(elem)
                path.append(elem)

        queue = sorted(queue, key=lambda k: k['h+g']) 
        state += 1

    return nmoves(path, state) if foundsolution else -1