bessie_y, bessie_x = map(int, input().split())
daisy_y, daisy_x = map(int, input().split())
destination_y, destination_x = map(int, input().split())

bessie_distance = max(abs(bessie_y - destination_y), abs(bessie_x - destination_x))
daisy_distance = abs(daisy_y - destination_y) + abs(daisy_x - destination_x)

if bessie_distance < daisy_distance:
  print('bessie')
elif daisy_distance < bessie_distance:
  print('daisy')
else:
  print('tie')
  
## ㄲㄲ