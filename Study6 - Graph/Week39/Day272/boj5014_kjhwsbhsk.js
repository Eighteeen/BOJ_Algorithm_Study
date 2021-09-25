const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `100 2 1 1 0`).split(
  '\n'
);

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const getMinBtnClicks = () => {
  const queue = [START_FLOOR];
  const btnClicks = new Array(TOTAL_FLOOR + 1).fill(NOT_VISITED);

  btnClicks[START_FLOOR] = 0;

  while (queue.length) {
    let floor = queue.shift();

    if (floor === TARGET_FLOOR) break;

    for (let nextFloor of [floor + UP_FLOOR, floor - DOWN_FLOOR]) {
      if (nextFloor < 1 || nextFloor > TOTAL_FLOOR) continue;
      if (btnClicks[nextFloor] > -1) continue;

      queue.push(nextFloor);
      btnClicks[nextFloor] = btnClicks[floor] + 1;
    }
  }

  return btnClicks[TARGET_FLOOR];
};

const [TOTAL_FLOOR, START_FLOOR, TARGET_FLOOR, UP_FLOOR, DOWN_FLOOR] = input().split(' ').map(Number);
const NOT_VISITED = -1;

let minBtnClicks = getMinBtnClicks();
console.log(minBtnClicks === -1 ? 'use the stairs' : minBtnClicks);
