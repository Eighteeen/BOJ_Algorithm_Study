horse_on_white = 0

for i in range(8):
  row = input()

  for j in range(i%2, 8, 2):
    if row[j] == 'F':
      horse_on_white += 1

print(horse_on_white)