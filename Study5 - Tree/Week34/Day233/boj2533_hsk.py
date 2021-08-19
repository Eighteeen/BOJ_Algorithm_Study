# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
## 세미콜론을 붙여도 에러는 나지 않지만 파이썬을 사용할 때는 파이썬에서 주로 사용하는 문법으로 사용하면 좋을 것 같습니다.
## => 아, 자꾸 js에서 복붙하다보니 세미콜론을 까먹네요.
## => 세미콜론을을 안쓰는게 파이썬 기본 문법이기 떄문에 변경할 경우 꼭 없애겠습니다.
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

def accumulateEarlyAdapter(node):
    visitedNodeList[node] = True
    earlyAdapter[node][0] = 1

    for nextNode in tree[node]:
        if visitedNodeList[nextNode]: continue

        ## 요 부분 배워갑니다
        accumulateEarlyAdapter(nextNode)
        earlyAdapter[node][0] += min(earlyAdapter[nextNode][0], earlyAdapter[nextNode][1])
        earlyAdapter[node][1] += earlyAdapter[nextNode][0]


## 깔끔해요 :22
N = int(input())
tree = [[] for _ in range(N + 1)]
earlyAdapter = [[0,0] for _ in range(N + 1)]
visitedNodeList = [False] * (N + 1)

for _ in range(N - 1):
    nodeA, nodeB = map(int, input().split())
    tree[nodeA].append(nodeB)
    tree[nodeB].append(nodeA)

accumulateEarlyAdapter(1)
print(min(earlyAdapter[1][0], earlyAdapter[1][1]))
