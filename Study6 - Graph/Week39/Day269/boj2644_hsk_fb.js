const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getDegreeOfKinshipByBFS = (startVertex) => {
  const visited = new Array(N + 1).fill(false);
  const queue = [[startVertex, 0]];

  while (queue.length) {
    const [vertex, degree] = queue.shift();

    if (vertex === targetVertexB) return degree;

    for (let nextVertex of peopleGraph[vertex]) {
      if (visited[nextVertex]) continue;
      visited[nextVertex] = true;

      queue.push([nextVertex, degree + 1]);
    }
  }

  return -1;
};

const N = parseInt(input());
const [targetVertexA, targetVertexB] = input().split(' ').map(Number);
const numOfRelationship = parseInt(input());
//// 한 가족 뿐만 아니라 여러 가족일 수 있어서 familyTree라는 표현은 어색해보여요. (Tree는 루트가 있으니)
//// Tree는 Graph로 바꾸면 좋을 것 같고, family는 복수형을 사용하거나 더 포괄적인 표현을 써도 좋을 것 같습니다! :22
//// => "한 가족 뿐만 아니라 여러 가족일 수 있다" 오,, 맞네요~~ 오오,,,,
//// => 반영했습니다.
const peopleGraph = Array.from(new Array(N + 1), () => new Array());

for (let i = 0; i < numOfRelationship; i++) {
  const [vertexA, vertexB] = input().split(' ').map(Number);

  peopleGraph[vertexA].push(vertexB);
  peopleGraph[vertexB].push(vertexA);
}

console.log(getDegreeOfKinshipByBFS(targetVertexA));
