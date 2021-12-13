while True:
  distance, speed_of_train, speed_of_plane = map(int, input().split())
  if distance + speed_of_train + speed_of_plane == 0:
    break;

  diff_seconds = distance * (1/speed_of_train - 1/speed_of_plane) * 60 * 60
  diff_seconds = round(diff_seconds)
  
  diff_minutes = diff_seconds // 60
  diff_seconds = diff_seconds % 60
  
  diff_hours = diff_minutes // 60
  diff_minutes = diff_minutes % 60

  print("{}:{:02d}:{:02d}".format(diff_hours, diff_minutes, diff_seconds))
