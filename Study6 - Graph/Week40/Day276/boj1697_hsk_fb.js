const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `5 17`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 함수 이름에서 sister로 추상화했으면 startNum도 조금 추상화해서 표현하는게 좋을 것 같슴다 : 22
//// startNum이랑 endNum 전부 매개변수로써 쓰이는 게 적절할 것 같아요 : 22 아니면 변수명을 더 구체적으로 써도 될 것 같아요
//// => 함수를 Graph에 맞게 변경하였고 밑에서 반환값을 getLeastTimeToFindSister으로 받도록 변경하였습니다.
const getMinDistanceFromStartToEnd = (startVertex, EndVertex) => {
  const visitedVertices = new Array(N + 1).fill(false);
  const distance = new Array(N + 1).fill(0);
  const queue = [startVertex];
  let queueCursor = 0;

  visitedVertices[startVertex] = true;

  while (queue.length > queueCursor) {
    let vertex = queue[queueCursor++];

    if (vertex === EndVertex) break;

    const calcVertex = [vertex - 1, vertex + 1, vertex * 2];
    for (let i = 0; i < 3; i++) {
      let nextVertex = calcVertex[i];

      if (nextVertex < 0 || nextVertex > 100000 || visitedVertices[nextVertex]) continue;
      visitedVertices[nextVertex] = true;

      distance[nextVertex] = distance[vertex] + 1;
      queue.push(nextVertex);
    }
  }

  return distance[EndVertex];
};

const [N, K] = input().split(' ').map(Number);
const getLeastTimeToFindSister = getMinDistanceFromStartToEnd(N, K);
console.log(getLeastTimeToFindSister);
