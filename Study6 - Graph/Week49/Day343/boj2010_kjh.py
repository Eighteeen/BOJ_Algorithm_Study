import sys

input = sys.stdin.readline

multitaps = int(input())

plugs = 0
for i in range(multitaps):
  plugs += int(input())

print(plugs - (multitaps - 1))
