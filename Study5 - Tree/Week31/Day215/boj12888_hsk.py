#같은 로직으로 nodejs에서는 실패가 떠서 python으로 작성하였습니다.
#js 실패 이유 : js에서는  Number.MAX_SAFE_INTEGER ( = 9007199254740991 ) 보다 큰 정수는
#오차가 있을 수 있기 때문에 정확하지 않은 값이 산출됩니다.
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