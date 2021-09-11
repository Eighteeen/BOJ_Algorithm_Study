def getTriangleType(sides):
    longestSide = max(sides)    
    otherSides = sum(sides) - longestSide;
    
    if longestSide >= otherSides:
        return "Invalid"
    
    sameSideCount = 0
    for i in range(2):
    	for j in range(i + 1, 3):
	        if sides[i] == sides[j]:
	            sameSideCount += 1
    
    if sameSideCount >= 2:
        return "Equilateral"
    elif sameSideCount == 1:
        return "Isosceles"
    return "Scalene"

## break에 세미콜론은 빼는 것이..
while True:
    sides = list(map(int, input().split()))
    if sides[0] == 0 and sides[1] == 0 and sides[2] == 0:
        break;
    print(getTriangleType(sides))

## 깔끔