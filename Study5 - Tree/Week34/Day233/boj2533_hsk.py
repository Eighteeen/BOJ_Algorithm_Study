# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
## 세미콜론을 붙여도 에러는 나지 않지만 파이썬을 사용할 때는 파이썬에서 주로 사용하는 문법으로 사용하면 좋을 것 같습니다.
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

def accumulateEarlyAdapter(node):
    visitedNodeList[node] = True
    earlyAdapter[node][0] = 1

    for nextNode in tree[node]:
        if visitedNodeList[nextNode]: continue

        accumulateEarlyAdapter(nextNode)
        earlyAdapter[node][0] += min(earlyAdapter[nextNode][0], earlyAdapter[nextNode][1]);
        earlyAdapter[node][1] += earlyAdapter[nextNode][0];


## 깔끔해요
N = int(input())
tree = [[] for _ in range(N + 1)]
earlyAdapter = [[0,0] for _ in range(N + 1)]
visitedNodeList = [False] * (N + 1)

for _ in range(N - 1):
    nodeA, nodeB = map(int, input().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

accumulateEarlyAdapter(1)
print(min(earlyAdapter[1][0], earlyAdapter[1][1]));
