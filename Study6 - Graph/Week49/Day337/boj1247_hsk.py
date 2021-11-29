import sys
input = sys.stdin.readline

for i in range(3):
  N = int(input())
  nums = [int(input()) for _ in range(N)]
  
  if sum(nums) == 0:
    print(0)
  elif sum(nums) > 0:
    print("+")
  else:
    print("-")