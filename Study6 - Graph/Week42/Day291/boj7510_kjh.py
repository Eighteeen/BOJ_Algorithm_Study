T = int(input())

for i in range(T):
  print("Scenario #%d:" % (i + 1))
  lengthsOfSides = list(map(int, input().split(' ')))
  lengthsOfSides.sort()

  if lengthsOfSides[2] ** 2 == lengthsOfSides[0] ** 2 + lengthsOfSides[1] ** 2:
    print("yes")
  else:
    print("no")
  
  print()

## 깔끔