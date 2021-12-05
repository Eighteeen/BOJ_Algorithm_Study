score = [0, 0]

for i in range(2):
  for j in range(3):
    score[i] += int(input()) * (3-j)

if score[0] > score[1]:
  print('A')
elif score[0] < score[1]:
  print('B')
else:
  print('T')
  
## ㄲㄲ : 22