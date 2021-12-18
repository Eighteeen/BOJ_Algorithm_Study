import sys
input = sys.stdin.readline

def calcByOperation(preVal, curVal, op):
    result = 0
    if op == '+':
        result = preVal + curVal
    if op == '-':
        result = preVal - curVal
    if op == '*':
        result = preVal * curVal
    return result

def setMinMaxValueInMap(x, y, curResult, operation):
    global maxOfCalculation, minOfCalculation
    if x == N - 1 and y == N - 1:
        maxOfCalculation = max(maxOfCalculation, curResult)
        minOfCalculation = min(minOfCalculation, curResult)
        return

    for i in range(2):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue
        
        if map[nx][ny].isdigit():
            calculationResult = calcByOperation(curResult, int(map[nx][ny]), operation)
            setMinMaxValueInMap(nx, ny, calculationResult, operation)
        else:
            setMinMaxValueInMap(nx, ny, curResult, map[nx][ny])


N = int(input())
map = list(input().split() for _ in range(N))

dx, dy = [1, 0] , [0, 1]
maxOfCalculation, minOfCalculation = -(5 ** 20), 5 ** 20

setMinMaxValueInMap(0, 0, int(map[0][0]), '')
print(maxOfCalculation, minOfCalculation)