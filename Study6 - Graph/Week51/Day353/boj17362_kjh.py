N = int(input())

iteration = [4, 3, 2, 1, 2, 3, 4, 5]

if N <= 5:
  print(N)
else:
  print(iteration[(N - 6) % 8])

## ㄲㄲ : 22