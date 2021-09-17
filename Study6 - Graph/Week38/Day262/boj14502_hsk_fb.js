const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 'DFS로 벽을 짓는다'만으로는 함수의 의도를 온전히 못 표현한 것 같습니당
//// => 추천해주신 chooseEachThreeWalls 으로 반영했습니다!
const chooseEachThreeWallsByDFS = (cntOfWall) => {
  if (cntOfWall === 3) {
    spreadVirusInMap();
    return;
  }

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (map[i][j] === BLANK) {
        //// 이욜 재귀를 활용한 브루트포스~
        //// => 어려웠다..
        map[i][j] = WALL;
        chooseEachThreeWallsByDFS(cntOfWall + 1);
        map[i][j] = BLANK;
      }
    }
  }
};

const spreadVirusInMap = () => {
  let infectedMap = Array.from(new Array(N), () => new Array(M).fill(0));
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      infectedMap[i][j] = map[i][j];
    }
  }

  let queue = [];
  let queueCursor = 0;

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (infectedMap[i][j] === VIRUS) queue.push([i, j]);
    }
  }

  let dx = [0, 0, 1, -1];
  let dy = [1, -1, 0, 0];

  while (queue.length > queueCursor) {
    let [x, y] = queue[queueCursor++];

    for (let i = 0; i < 4; i++) {
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (infectedMap[nx][ny] !== BLANK) continue;

      infectedMap[nx][ny] = VIRUS;
      queue.push([nx, ny]);
    }
  }

  setMaxSafetyBoundary(infectedMap);
};

//// 감염을 모두 시키고나서 호출돼야만 의미가 있는 함수라서 안전지대를 찾는 함수라기보단 감염되지 않은 타일의 수를 세는 함수인 것 같습니다
//// => virusMap 이라는 변수가 조금 중의적인 표현으로 쓰여저서 헷갈리게 한 것 같아요!
//// => 이 함수는 매개변수로 전달된 감염된 Map에서에 0의 개수를 찾는 것이기 때문에 함수명은 유지하고
//// => 매개변수명을 infectedMap으로 변경하여 반영했습니다.

//// 그리고 전역변수에 대입을 하는거라 리턴값이 있을 것 같은 get보단 fill, set, update 등을 사용하는게 좋을 것 같아요
//// => 넹! 피드백 감사해요. 반영했습니다.
const setMaxSafetyBoundary = (infectedMap) => {
  let cntOfBLANK = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (infectedMap[i][j] === BLANK) cntOfBLANK += 1;
    }
  }

  maxSafetyBoundary = Math.max(maxSafetyBoundary, cntOfBLANK);
};

const [N, M] = input().split(' ').map(Number);
let map = Array.from(new Array(N), () => input().split(' ').map(Number));

//// 이욜 상수 한번에 선언...
//// => 훗 js로 넘어오슈
const [BLANK, WALL, VIRUS] = [0, 1, 2];
let maxSafetyBoundary = 0;

//// 아래의 2중 for문 빼고 buildWallByDFS(0); 해도 맞았습니다 나오네용
//// => 아 0부터 하면 되는군요,,,,ㅠㅠ.. 매우 감사합니다,,,(분명 중복돼서 방법이 있을 것 같았는데 이런방법이.. 왜 생각을 못했을까,, 처음 코드를 버렸어야..,,,)
chooseEachThreeWallsByDFS(0);

console.log(maxSafetyBoundary);
