# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)


def calcMaxDepthAbleToDeliver(curNode):
    maxDepth = 0

    for nextNode in tree[curNode]:
        if (visitedNodes[nextNode]): continue
        visitedNodes[nextNode] = True
        
        maxDepth = max(maxDepth, calcMaxDepthAbleToDeliver(nextNode) + 1)

    maxDepthOfNodeList[curNode] = maxDepth
    return maxDepth

def getTotalDistanceToMove():
    distance = 0
    
    for i in range(1, N + 1):
        if maxDepthOfNodeList[i] < D or i == S: continue;        
        distance += 1;
    
    return distance * 2;

N, S, D = map(int, input().split())
tree = [[] for _ in range(N + 1)]
## 항상 파이썬 문법 배워가요
maxDepthOfNodeList = [0] * (N + 1)
visitedNodes = [False] * (N + 1)

for _ in range(N - 1):
    nodeA, nodeB = map(int, input().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

## 세미콜론 있어도 괜찮아요?
visitedNodes[S] = True;
calcMaxDepthAbleToDeliver(S);
print(getTotalDistanceToMove())