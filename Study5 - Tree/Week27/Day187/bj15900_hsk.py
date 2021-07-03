# nodejs 에서 시간초과와 재귀오류를 해결하지 못하여 python으로 풀었음
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

## 모두 구하는 방법으로도 가능하군요! 다른 방법도 알아갑니다.
sumDepth = sum(depthFromRootList)

if sumDepth % 2:
    print('Yes')
else:
    print('No')
