from undirected_graph import UndirectedGraph as Graph


class CC:
    def __init__(self, graph: Graph):
        self._marked = [False] * graph.v()
        self._id = [None] * graph.v()
        self._count = 0
        for v in range(graph.v()):
            if not self._marked[v]:
                self.dfs(graph, v)
                self._count += 1

    def dfs(self, graph: Graph, v: int):
        self._marked[v] = True
        self._id[v] = self._count
        for w in graph.adj(v):
            if not self._marked[w]:
                self.dfs(graph, w)

    def connected(self, v: int, w: int) -> bool:
        return self._id[v] == self._id[w]

    def count(self) -> int:
        return self._count


if __name__ == "__main__":
    g = Graph.load_file("tiny.txt")
    cc = CC(g)
    print(cc.count())
