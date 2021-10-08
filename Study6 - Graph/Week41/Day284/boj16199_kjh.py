birthday_year, birthday_month, birthday_day = map(int, input().split(' '))
now_year, now_month, now_day = map(int, input().split(' '))

man_age = now_year - birthday_year - 1
if now_month > birthday_month or (now_month == birthday_month and now_day >= birthday_day):
    man_age += 1

seneun_age = now_year - birthday_year + 1
yeon_age = now_year - birthday_year

print(man_age)
print(seneun_age)
print(yeon_age)

## 깔끔