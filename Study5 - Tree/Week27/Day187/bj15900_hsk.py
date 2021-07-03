import sys


N = int(input())
tree = [[] for _ in range(N + 1)]
visitedNode = [0] * (N + 1)
depthFromRootList = []
nodeAndDepthArr = [[1, 0]]

for i in range(N - 1):
    nodeA, nodeB = map(int, sys.stdin.readline().rstrip().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

while nodeAndDepthArr:
    nodeNum, depth = nodeAndDepthArr.pop()
    visitedNode[nodeNum] = 1

    connectedNodeList = tree[nodeNum]
    if nodeNum != 1 and len(connectedNodeList) == 1:
        depthFromRootList.append(depth)
        continue

    for node in connectedNodeList:
        if visitedNode[node] == 0:
            nodeAndDepthArr.append([node, depth + 1])

sumDepth = sum(depthFromRootList)

if sumDepth % 2:
    print('Yes')
else:
    print('No')
