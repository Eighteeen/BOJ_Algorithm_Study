people_per_square_meter, area = map(int, input().split(' '))
people = people_per_square_meter * area

people_articles = map(int, input().split(' '))
for article in people_articles:
  print(article - people, end=' ')
  
# 깔끔 : 22