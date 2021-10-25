def get_quadrant_idx(x, y):
  if x > 0 and y > 0:
    return 0
  if x < 0 and y > 0:
    return 1
  if x < 0 and y < 0:
    return 2
  if x > 0 and y < 0:
    return 3
  if x == 0 or y == 0:
    return 4

points_quadrant = [0, 0, 0, 0, 0]

T = int(input())

for i in range(T):
  x, y = map(int, input().split(' '))
  quadrant_idx = get_quadrant_idx(x, y)

  points_quadrant[quadrant_idx] += 1

for i in range(4):
  print('Q{}: {}'.format(i+1, points_quadrant[i]))

print('AXIS: {}'.format(points_quadrant[4]))
