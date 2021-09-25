const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `5
1 4 2 2 1
3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const traversalInStonesByBFS = (startStone) => {
  let queue = [startStone];
  visitedStones[startStone] = true;

  while (queue.length) {
    const stone = queue.shift();

    for (let jumpDistance of [-Ai[stone], Ai[stone]]) {
      let nextStone = stone + jumpDistance;

      if (nextStone < 0 || nextStone >= N || visitedStones[nextStone]) continue;
      visitedStones[nextStone] = true;

      queue.push(nextStone);
    }
  }
};

const N = parseInt(input());
const Ai = input().split(' ').map(Number);
const S = parseInt(input());
let visitedStones = new Array(N).fill(false);

traversalInStonesByBFS(S - 1);
console.log(visitedStones.filter((isVisited) => isVisited).length);

//// 왕 깔끔 :2