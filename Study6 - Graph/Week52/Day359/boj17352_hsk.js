const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 2
2 3
4 5`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getNotVisitedNumOfIsland = (startVertex) => {
  const visitedIslands = new Array(N + 1).fill(false);
  const queue = [startVertex];
  let queueCursor = 0;

  visitedIslands[startVertex] = true;

  while (queue.length > queueCursor) {
    const island = queue[queueCursor++];

    for (let nextIsland of graphOfIslands[island]) {
      if (visitedIslands[nextIsland]) continue;

      visitedIslands[nextIsland] = true;
      queue.push(nextIsland);
    }
  }

  let notVisitedNumOfIsland = visitedIslands.slice(1).findIndex((visited) => !visited) + 1;
  return notVisitedNumOfIsland;
};

const N = parseInt(input());
const graphOfIslands = Array.from(Array(N + 1), () => new Array());
for (let i = 0; i < N - 2; i++) {
  const [islandA, islandB] = input().split(' ').map(Number);
  graphOfIslands[islandA].push(islandB);
  graphOfIslands[islandB].push(islandA);
}

//// 올.. 그냥 하나만 방문시도해도 충분하네요 배워갑니다
console.log(1, getNotVisitedNumOfIsland(1));
//// 깔쌈
