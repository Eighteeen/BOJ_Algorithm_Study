while True:
  num1, num2 = map(int, input().split(' '))
  if num1 + num2 == 0:
    break

  if num1 > num2:
    print('Yes')
  else:
    print('No')