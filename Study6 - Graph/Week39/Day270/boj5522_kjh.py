import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9) ## 이거 지우시는게,, : 22 => 켱씨 main 갖다쓴거 티내려고 안 지움 ㅎ

sum = 0
for i in range(5):
    sum += int(input())

print(sum)

## 깔끔 : 22