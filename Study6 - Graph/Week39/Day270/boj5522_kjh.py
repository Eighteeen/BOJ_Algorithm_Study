import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

sum = 0
for i in range(5):
    sum += int(input())

print(sum)