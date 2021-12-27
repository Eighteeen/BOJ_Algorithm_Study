const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 6
1 1 2 2
2 2 3 3
3 3 4 4
2 2 1 2
3 3 3 2
4 4 4 3`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMaxRoomsLightOn = () => {
  const visited = Array.from(new Array(N + 1), () => new Array(N + 1).fill(false));
  const lightOnInRoom = Array.from(new Array(N + 1), () => new Array(N + 1).fill(LIGHT_OFF));
  let roomsLightOn = 1;

  visited[1][1] = true;
  lightOnInRoom[1][1] = LIGHT_ON;

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];
  const queue = [[1, 1]];

  while (queue.length) {
    const [currX, currY] = queue.shift();
    for (let [roomX, roomY] of buttonHasRooms[currX][currY]) {
      if (!lightOnInRoom[roomX][roomY]) {
        lightOnInRoom[roomX][roomY] = LIGHT_ON;
        roomsLightOn += 1;

        //// nextRoom 과 nextCurrRoom 변수 의미 구분이 한번에 파악이 안 돼서 아쉬웠어요.
        //// nextLightOnRoom 정도로 작명하면 명확한 의미 구분이 될 것 같습니다.
        //// => 반영했어요
        for (let i = 0; i < 4; i++) {
          let nextLightOnRoomX = roomX + dx[i];
          let nextLightOnRoomY = roomY + dy[i];

          if (nextLightOnRoomX < 1 || nextLightOnRoomY < 1 || nextLightOnRoomX > N || nextLightOnRoomY > N) continue;
          if (visited[nextLightOnRoomX][nextLightOnRoomY]) {
            queue.push([nextLightOnRoomX, nextLightOnRoomY]);
          }
        }
      }
    }

    for (let i = 0; i < 4; i++) {
      let nextCurrRoomX = currX + dx[i];
      let nextCurrRoomY = currY + dy[i];

      if (nextCurrRoomX < 1 || nextCurrRoomY < 1 || nextCurrRoomX > N || nextCurrRoomY > N) {
        continue;
      }
      if (!visited[nextCurrRoomX][nextCurrRoomY] && lightOnInRoom[nextCurrRoomX][nextCurrRoomY]) {
        queue.push([nextCurrRoomX, nextCurrRoomY]);
        visited[nextCurrRoomX][nextCurrRoomY] = true;
      }
    }
  }

  return roomsLightOn;
};

const [LIGHT_OFF, LIGHT_ON] = [false, true];
const [N, M] = input().split(' ').map(Number);
const buttonHasRooms = Array.from(new Array(N + 1), () => new Array(N + 1));

for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= N; j++) {
    buttonHasRooms[i][j] = [];
  }
}

for (let i = 0; i < M; i++) {
  const [x, y, a, b] = input().split(' ').map(Number);
  buttonHasRooms[x][y].push([a, b]);
}

console.log(getMaxRoomsLightOn());
