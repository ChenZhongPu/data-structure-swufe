class UndirectedGraph:
    def __init__(self, v):
        self._edges = [[] for _ in range(v)]
        self._v = v
        self._e = 0

    @staticmethod
    def load_file(file_path):
        with open(file_path) as f:
            v = int(f.readline())
            g = UndirectedGraph(v)
            e = int(f.readline())
            for _ in range(e):
                v, w = f.readline().split()
                g.add_edge(int(v), int(w))
        return g

    def v(self):
        return self._v

    def e(self):
        return self._e

    def adj(self, v):
        return iter(self._edges[v])

    def add_edge(self, v, w):
        self._edges[v].append(w)
        self._edges[w].append(v)
        self._e += 1

    def degree(self, v):
        return len(self._edges[v])

    def number_of_self_loops(self):
        count = 0
        for v in range(self._v):
            for w in self.adj(v):
                if v == w:
                    count += 1
        return count // 2

    def __str__(self):
        s = f"{self._v} vertices, {self._e} edges\n"
        for v in range(self._v):
            s += f"{v}: "
            for w in self.adj(v):
                s += f"{w} "
            s += "\n"
        return s


if __name__ == "__main__":
    g = UndirectedGraph(7)
    g.add_edge(0, 5)
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(0, 6)
    g.add_edge(3, 4)
    g.add_edge(3, 5)
    g.add_edge(4, 5)
    g.add_edge(4, 6)
    print(g)

    g2 = UndirectedGraph.load_file("tiny.txt")
    print(g2)
