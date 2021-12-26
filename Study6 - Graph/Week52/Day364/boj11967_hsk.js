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

        for (let i = 0; i < 4; i++) {
          let nextRoomX = roomX + dx[i];
          let nextRoomY = roomY + dy[i];

          if (nextRoomX < 1 || nextRoomY < 1 || nextRoomX > N || nextRoomY > N) continue;
          if (visited[nextRoomX][nextRoomY]) {
            queue.push([nextRoomX, nextRoomY]);
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
