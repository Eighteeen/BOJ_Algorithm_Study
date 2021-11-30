import sys
input = sys.stdin.readline

for i in range(3):
  N = int(input())

  number = 0
  for j in range(N):
    number += int(input())
  
  if number == 0:
    print(0)
  elif number < 0:
    print('-')
  else:
    print('+')
    
## 깔끔