people_per_square_meter, area = map(int, input().split(' '))
number_of_people = people_per_square_meter * area

articles_with_number_of_people = map(int, input().split(' '))
for article in articles_with_number_of_people:
  