import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

num = 1
def traversalInOrder(depth, idx):
    global num
    if idx >= len(treeByDepth[depth]):
        return;

    traversalInOrder(depth + 1, idx * 2)    
    sequenceInTree[treeByDepth[depth][idx]] = num
    num += 1
    traversalInOrder(depth + 1, idx * 2 + 1)


N = int(input())
depthArr = list(map(int, input().split()))
treeByDepth = [[] for _ in range(N + 1)]
isPossibleSequence = True;
sequenceInTree = [0] * (N +1);

treeByDepth[0].append(1);

for idx, depth in enumerate(depthArr):
    treeByDepth[depth].append(idx + 2)

    if len(treeByDepth[depth - 1]) * 2 < len(treeByDepth[depth]):
        isPossibleSequence = False;

if isPossibleSequence:
    traversalInOrder(0, 0)
    print(' '.join(map(str,sequenceInTree[1:])))
else:
    print('-1')
