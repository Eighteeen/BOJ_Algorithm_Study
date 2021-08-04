# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

class TreeOfIslands:
    def __init__(self):
        self.cntAnimals = 0
        self.connectedIsland = []

## 깔끔해요
## 이제 재귀 활용이 꽤나 자연스러워진 것 같아요.
def getNumOfRescuedSheeps(curNode):
    numOfRescuedSheeps = int(treeOfIslands[curNode].cntAnimals)

    for nextIsland in treeOfIslands[curNode].connectedIsland:
        numOfRescuedSheeps += getNumOfRescuedSheeps(nextIsland)

    if numOfRescuedSheeps < 0: numOfRescuedSheeps = 0
    
    return numOfRescuedSheeps


numOfIslands = int(input())
treeOfIslands = [TreeOfIslands() for _ in range(numOfIslands + 1)]

for i in range(2,numOfIslands + 1 ):
    isWolf, numOfAnimals, island = input().split(' ')

    ## 처음부터 음수 설정! 생각지 못 한 방법이네요. 다른 방법도 알아갑니다.
    treeOfIslands[i].cntAnimals = (-int(numOfAnimals) if isWolf == 'W' else int(numOfAnimals))
    treeOfIslands[int(island)].connectedIsland.append(i)

print(getNumOfRescuedSheeps(1))