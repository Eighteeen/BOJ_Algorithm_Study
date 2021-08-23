# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

num = 1
## 알고리즘 돌아가는 것은 비슷한데 구현 방법이 달라서 흥미로웠습니다!
## idx 에 대한 설명이 더 구체적이었으면 좋겠어요! 삽입 순서가 아니라 treeByDepth 자체에 쓰이는 걸로 착각해서 해석이 오래걸렸습니다. ㅠㅠ
## => 아 그렇게 느낄 수도 있겠네요! 감사해용!
def traversalInOrder(depth, sequenceNum):
    global num
    if sequenceNum >= len(treeByDepth[depth]):
        return

    traversalInOrder(depth + 1, sequenceNum * 2)    
    sequenceInTree[treeByDepth[depth][sequenceNum]] = num
    num += 1
    traversalInOrder(depth + 1, sequenceNum * 2 + 1)


N = int(input())
depthArr = list(map(int, input().split()))
treeByDepth = [[] for _ in range(N + 1)]
isPossibleSequence = True
sequenceInTree = [0] * (N +1)

treeByDepth[0].append(1)

for idx, depth in enumerate(depthArr):
    treeByDepth[depth].append(idx + 2)

    ## 해당 조건문에서 break해서 최적화하면 좋을 것 같습니다.
    ## => 오호 사소한 부분 캐치 감사해영
    if len(treeByDepth[depth - 1]) * 2 < len(treeByDepth[depth]):
        isPossibleSequence = False
        break

if isPossibleSequence:
    traversalInOrder(0, 0)
    print(' '.join(map(str,sequenceInTree[1:])))
else:
    print('-1')
