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
## combinations 함수가 있는 지 몰랐어요. 활용 굿굿 깔끔! :22 ㅇㅗㄹ.. ㅆㅓ머ㄱㅓㅂㅘㅑㅈㅣ
