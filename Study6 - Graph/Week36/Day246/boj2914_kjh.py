import math

songAmount, targetAverage = map(int, input().split(' '))

rangeStart = songAmount * (targetAverage - 1)
rangeEnd = songAmount * targetAverage;

for melodyAmount in range(rangeStart, rangeEnd + 1):
    average = math.ceil(melodyAmount / songAmount)
    if average == targetAverage:
        print(melodyAmount)
        break;

## 예전에 푼 것 같지만 조금 어렵게 푸신 것 같네요 ㅎㅎ