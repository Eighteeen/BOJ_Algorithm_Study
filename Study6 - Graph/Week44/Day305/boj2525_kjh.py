hour, minute = map(int, input().split(' '))
required_minutes = int(input())

hour += (minute + required_minutes) // 60
minute = (minute + required_minutes) % 60
hour = hour % 24

print(hour, minute)

# ㄲㄲ