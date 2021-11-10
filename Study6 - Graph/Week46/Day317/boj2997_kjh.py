a, b, c = sorted(map(int, input().split(' ')))

frontDiff = b-a;
backDiff = c-b;

if frontDiff == backDiff:
  print(c + frontDiff)
elif frontDiff > backDiff:
  print(a + backDiff)
elif frontDiff < backDiff:
  print(b + frontDiff)
## 깔끔