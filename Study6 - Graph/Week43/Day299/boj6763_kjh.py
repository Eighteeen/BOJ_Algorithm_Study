speed_limit = int(input())
speed_current = int(input())

exceeded_speed = speed_current - speed_limit

if exceeded_speed <= 0:
  print('Congratulations, you are within the speed limit!')
  quit()

fine = 0
if exceeded_speed >= 1 and exceeded_speed <= 20:
  fine = 100
elif exceeded_speed >= 21 and exceeded_speed <= 30:
  fine = 270
else:
  fine = 500

print('You are speeding and your fine is $%d.' % fine)