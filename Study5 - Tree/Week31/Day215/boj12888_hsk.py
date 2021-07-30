#같은 로직으로 nodejs에서는 실패가 떠서 python으로 작성하였습니다.
import sys
input = sys.stdin.readline

H = int(input())
numOfCarNeeded = [1] * (H + 2)
numOfCarNeeded[0] = 1
numOfCarNeeded[1] = 1

for i in range(2, H + 1):
    if i % 2 == 0:
        numOfCarNeeded[i] = 2 * numOfCarNeeded[i - 1] + 1;
    else:
        numOfCarNeeded[i] = 2 * numOfCarNeeded[i - 1] - 1;

print(numOfCarNeeded[H])