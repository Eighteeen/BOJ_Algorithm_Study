import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

## JS 눈물.. 깔끔합니다
def setNestingSetList(startNode, prevNode):
    global order
    nestingSetList[startNode][0] = order
    order += 1
    
    tree[startNode].sort()
    for nextNode in tree[startNode]:
        if nextNode == prevNode:
            continue
        setNestingSetList(nextNode, startNode)
    
    nestingSetList[startNode][1] = order
    order += 1


N = int(input())
tree = [[] for _ in range(N + 1)]

nestingSetList = [[0,0] for _ in range(N + 1)]
order = 1

for _ in range(N):
    treeInfo = list(map(int, input().split()))
    nodeNum = treeInfo[0]

    for node in range(1, len(treeInfo)):
        if treeInfo[node] == -1:
            continue
        tree[nodeNum].append(treeInfo[node])

rootNum = int(input())
setNestingSetList(rootNum, 0)

for i in range(1, len(nestingSetList)):
    print(i, nestingSetList[i][0], nestingSetList[i][1])