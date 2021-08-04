# nodejs로 stack size exceeded 문제로 실패하여 python으로 풀이
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

class TreeOfIslands:
    def __init__(self):
        self.cntAnimals = 0
        self.connectedIsland = []

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

    treeOfIslands[i].cntAnimals = (-int(numOfAnimals) if isWolf == 'W' else int(numOfAnimals))
    treeOfIslands[int(island)].connectedIsland.append(i)

print(getNumOfRescuedSheeps(1))