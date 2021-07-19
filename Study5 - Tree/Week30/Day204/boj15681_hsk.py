import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)


def calcSubTreeNodeCnt(vertex):
    subTreeCnt[vertex] = 1
    visitedNode[vertex] = True

    for node in tree[vertex]:
        if visitedNode[node]:
            continue

        calcSubTreeNodeCnt(node)
        subTreeCnt[vertex] += subTreeCnt[node]


nodeNum, rootNum, queryNum = map(int, input().split())
tree = [[] for _ in range(nodeNum + 1)]
subTreeCnt = [0] * (nodeNum + 1)
visitedNode = [False] * (nodeNum + 1)

for _ in range(nodeNum - 1):
    nodeA, nodeB = map(int, input().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

calcSubTreeNodeCnt(rootNum)

for _ in range(queryNum):
    vertexNum = int(input())
    print(subTreeCnt[vertexNum])
