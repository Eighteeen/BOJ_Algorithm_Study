def get_operator(operand1, operand2, result):
  if operand1 + operand2 == result:
    return '+'
  if operand1 - operand2 == result:
    return '-'
  if operand1 * operand2 == result:
    return '*'
  if operand1 / operand2 == result:
    return '/'
  return None

a, b, c = map(int, input().split(' '))

operator = get_operator(a, b, c)
if type(operator) is str:
  print('{}{}{}={}'.format(a, operator, b, c))
else:
  operator = get_operator(b, c, a)
  print('{}={}{}{}'.format(a, b, operator, c))

## 오깔끔 : 22