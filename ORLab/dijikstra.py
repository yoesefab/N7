import heapq
from collections import defaultdict

EDGES = [
    ('A', 'D', 14),
    ('A', 'B', 12),
    ('D', 'E', 10),
    ('E', 'C', 13),
    ('E', 'F', 16),
    ('E', 'H', 10),
    ('C', 'F', 10),
    ('F', 'H', 14),
    ('F', 'B', 21),
    ('B', 'G', 16),
    ('G', 'H', 11),
]

def build_graph(edges):
    graph = defaultdict(list)
    for u, v, w in edges:
        graph[u].append((v, w))
        graph[v].append((u, w))
    return graph


def dijkstra(graph, source):
    dist = {node: float('inf') for node in graph}
    prev = {node: None for node in graph}
    dist[source] = 0

    pq = [(0, source)]
    visited = set()
    steps = []

    while pq:
        d, u = heapq.heappop(pq)
        if u in visited:
            continue
        visited.add(u)

        steps.append({
            'etape':  len(steps) + 1,
            'visite': u,
            'dist':   dict(dist),
            'prev':   dict(prev),
        })

        for v, w in graph[u]:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                prev[v] = u
                heapq.heappush(pq, (dist[v], v))

    return dist, prev, steps


def get_path(prev, source, target):
    path, node = [], target
    while node is not None:
        path.append(node)
        node = prev.get(node)
    path.reverse()
    return path if path and path[0] == source else []


def extract_weights(path, graph):
    result = []
    for i in range(len(path) - 1):
        u, v = path[i], path[i + 1]
        for neighbor, w in graph[u]:
            if neighbor == v:
                result.append(w)
                break
    return result


def print_iterations(steps, all_nodes):
    col_w  = 14
    header = f"{'Etape':^6} | {'Visite':^7} | " + " | ".join(f"{n:^{col_w}}" for n in all_nodes)
    sep    = "-" * len(header)

    print("\n" + "=" * len(header))
    print(" TABLEAU DES ITERATIONS ")
    print("=" * len(header))
    print(header)
    print(sep)

    for s in steps:
        cells = []
        for n in all_nodes:
            d = s['dist'].get(n, float('inf'))
            p = s['prev'].get(n)
            if d == float('inf'):
                cells.append(f"{'inf':^{col_w}}")
            elif p is None:
                cells.append(f"{str(d):^{col_w}}")
            else:
                cells.append(f"{(str(d) + ' (' + p + ')'):^{col_w}}")
        print(f"{s['etape']:^6} | {s['visite']:^7} | " + " | ".join(cells))

    print("=" * len(header) + "\n")


def print_result(source, target, path, total_dist, weights):
    arrow  = " -> ".join(path)
    decomp = " + ".join(str(w) for w in weights) + f" = {total_dist}"
    print("+" + "-" * 58 + "+")
    print(f"|  Chemin optimal  {source} -> {target:<39}|")
    print("+" + "-" * 58 + "+")
    print(f"|  Trajet      : {arrow:<43}|")
    print(f"|  Detail      : {decomp:<43}|")
    print(f"|  Poids total : {str(total_dist):<43}|")
    print("+" + "-" * 58 + "+\n")


def main():
    graph     = build_graph(EDGES)
    all_nodes = sorted(graph.keys())

    dist, prev, steps = dijkstra(graph, 'A')

    print_iterations(steps, all_nodes)

    path_AH = get_path(prev, 'A', 'H')
    print_result('A', 'H', path_AH, dist['H'], extract_weights(path_AH, graph))

    path_AC = get_path(prev, 'A', 'C')
    print_result('A', 'C', path_AC, dist['C'], extract_weights(path_AC, graph))

    print("Distances minimales depuis A :")
    print("-" * 40)
    for node in all_nodes:
        p = get_path(prev, 'A', node)
        print(f"  A -> {node} : {str(dist[node]):>4}   chemin : {' -> '.join(p)}")
    print()


if __name__ == "__main__":
    main()
