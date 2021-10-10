const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `52 9 16`).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [D_LENGTH, H_RATIO, W_RATIO] = input().split(' ').map(Number);

//// 이런 방법도 있군요 알아가요!
const D_RATIO = Math.sqrt(H_RATIO * H_RATIO + W_RATIO * W_RATIO);
const x = (W_RATIO * D_LENGTH) / D_RATIO;
const y = (H_RATIO * D_LENGTH) / D_RATIO;

console.log(Math.floor(y), Math.floor(x));
//// 깔끔