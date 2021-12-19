X_FARE_PER_LITER = int(input())
Y_BASIC_FARE = int(input())
Y_BASIC_LITERS = int(input())
Y_FARE_PER_LITER = int(input())
USED_LITERS = int(input())

x_fare = X_FARE_PER_LITER * USED_LITERS
y_fare = Y_BASIC_FARE

if USED_LITERS > Y_BASIC_LITERS:
  y_fare += (USED_LITERS - Y_BASIC_LITERS) * Y_FARE_PER_LITER

print(min(x_fare, y_fare))
## 깔끔 : 22