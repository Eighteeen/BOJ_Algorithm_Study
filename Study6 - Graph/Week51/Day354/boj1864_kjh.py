octopus_digit = {
  '-': 0,
  '\\': 1,
  '(': 2,
  '@': 3,
  '?': 4,
  '>': 5,
  '&': 6,
  '%': 7,
  '/': -1
}

while True:
  octopus_number = input()
  if octopus_number == '#':
    break

  decimal_number = 0
  for i in range(len(octopus_number)):
    number = octopus_number[-i-1]
    decimal_number += (8 ** i) * octopus_digit[number]

  print(decimal_number)
  
## 깔끔