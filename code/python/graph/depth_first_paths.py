from undirected_graph import UndirectedGraph as Graph


class DepthFirstPaths:
    def __init__(self, graph: Graph, s):
        self._marked = [False] * graph.v()
        self._edge_to = [None] * graph.v()
        self._source = s
        self.dfs(graph, s)

    def dfs(self, graph: Graph, v):
        self._marked[v] = True
        for w in graph.adj(v):
            if not self._marked[w]:
                self._edge_to[w] = v
                self.dfs(graph, w)

    def has_path_to(self, v):
        return self._marked[v]

    def path_to(self, v):
        if not self.has_path_to(v):
            return None
        path = []
        x = v
        while x != self._source:
            path.append(x)
            x = self._edge_to[x]
        path.append(self._source)
        return path


if __name__ == "__main__":
    g = Graph.load_file("tiny_cg.txt")
    dfp = DepthFirstPaths(g, 0)
    path = dfp.path_to(5)
    for v in path:
        print(v, end=" ")
    print()
