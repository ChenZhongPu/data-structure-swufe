from undirected_graph import UndirectedGraph as Graph


class DFS:
    def __init__(self, g: Graph, s):
        self._marked = [False] * g.v()
        self._count = 0
        self.dfs(g, s)

    def dfs(self, g: Graph, v):
        self._marked[v] = True
        self._count += 1
        for w in g.adj(v):
            if not self._marked[w]:
                self.dfs(g, w)

    def marked(self, w):
        return self._marked[w]

    def count(self):
        return self._count


if __name__ == "__main__":
    g = Graph.load_file("tiny.txt")
    search = DFS(g, 0)
    print(search.count())
