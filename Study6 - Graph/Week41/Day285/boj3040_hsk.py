import sys
from itertools import combinations
input = sys.stdin.readline

numOfDwarfs = []
for _ in range(9):
    numOfDwarfs.append(int(input()))

totalOfNotDwarf = sum(numOfDwarfs) - 100
for combInDwarf in combinations(numOfDwarfs, 2):
    if sum(combInDwarf) == totalOfNotDwarf:
        numOfDwarfs.remove(combInDwarf[0])
        numOfDwarfs.remove(combInDwarf[1])

result = "\n".join(map(str,numOfDwarfs))
print(result)