from undirected_graph import UndirectedGraph as Graph


class Stack:
    def __init__(self):
        self._s = []

    def push(self, item):
        self._s.append(item)

    def pop(self):
        return self._s.pop()

    def peek(self):
        return self._s[-1]

    def __len__(self):
        return len(self._s)


class DFS:
    def __init__(self, g: Graph, s):
        self._marked = [False] * g.v()
        self._count = 0
        self.dfs(g, s)

    def dfs(self, g: Graph, v):
        adj_iters = [g.adj(v) for v in range(g.v())]
        stack = Stack()
        stack.push(v)
        self._marked[v] = True
        self._count += 1
        while stack:
            v = stack.peek()
            try:
                w = next(adj_iters[v])
                if not self._marked[w]:
                    stack.push(w)
                    self._marked[w] = True
                    self._count += 1
            except StopIteration:
                stack.pop()

    def marked(self, w):
        return self._marked[w]

    def count(self):
        return self._count


if __name__ == "__main__":
    g = Graph.load_file("tiny.txt")
    search = DFS(g, 0)
    print(search.count())
