from collections import deque


def is_valid(x, y, maze):
    return 0 <= x < len(maze) and 0 <= y < len(maze[0]) and maze[x][y] == 0


def bfs(maze, start, end):
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    queue = deque([start])
    visited = set()

    while queue:
        current = queue.popleft()
        if current == end:
            return True
        if current in visited:
            continue
        visited.add(current)
        for direction in directions:
            next_x, next_y = current[0] + direction[0], current[1] + direction[1]
            if is_valid(next_x, next_y, maze):
                queue.append((next_x, next_y))
    return False


maze = [
    [0, 1, 0, 0, 0],
    [0, 1, 0, 1, 0],
    [0, 0, 0, 1, 0],
    [1, 1, 1, 1, 1],
    [0, 0, 0, 0, 0],
]

start = (0, 0)
end = (4, 4)

result = bfs(maze, start, end)
print(result)
