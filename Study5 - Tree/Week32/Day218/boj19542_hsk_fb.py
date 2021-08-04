# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

## 무난
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
        ## or 구분 문장 각자에 괄호가 있으면 가독성이 높아질 것 같습니다.
        ## => 음. 이부분에서는 이미 충분히 가독성이 좋다고 생각하기 떄문에 바꾸지 않겠습니다.
        if maxDepthOfNodeList[i] < D or i == S: continue        
        distance += 1
    
    return distance * 2

N, S, D = map(int, input().split())
tree = [[] for _ in range(N + 1)]
## 항상 파이썬 문법 배워가요
maxDepthOfNodeList = [0] * (N + 1)
visitedNodes = [False] * (N + 1)

for _ in range(N - 1):
    nodeA, nodeB = map(int, input().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

## 세미콜론 있어도 괜찮아요? : 22 괜찮더라도 없으면 아예 없애는 게 깔끔할 것 같아요
## => 본능적으로 적은거지만, 문법 오류는 안납니다(원래는 없는게 맞아요)
visitedNodes[S] = True
calcMaxDepthAbleToDeliver(S)
print(getTotalDistanceToMove())