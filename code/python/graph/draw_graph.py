import networkx as nx
import matplotlib.pyplot as plt

if __name__ == "__main__":
    adj = {
        0: (6, 2, 5, 1),
        1: (0,),
        2: (0,),
        3: (4, 5),
        4: (5, 6, 3),
        5: (3, 4, 0),
        6: (0, 4),
    }
    G = nx.Graph(adj)
    nx.draw(G, with_labels=True, font_weight="bold")
    plt.savefig("Graph.png", format="PNG")
