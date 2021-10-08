birthday_year, birthday_month, birthday_day = map(int, input().split(' '))
now_year, now_month, now_day = map(int, input().split(' '))

## now_year - birthday_year 을 변수로 저장해서 써서 연산을 줄이는 것도 좋을 것 같습니다.
man_age = now_year - birthday_year - 1
if now_month > birthday_month or (now_month == birthday_month and now_day >= birthday_day):
    man_age += 1

seneun_age = now_year - birthday_year + 1
yeon_age = now_year - birthday_year

print(man_age)
print(seneun_age)
print(yeon_age)

## 깔끔