const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4 5
5 4
4 4`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [Br, Bc] = input().split(' ').map(Number);
const [Dr, Dc] = input().split(' ').map(Number);
const [Jr, Jc] = input().split(' ').map(Number);

const distanceOfB = Math.max(Math.abs(Jr - Br), Math.abs(Jc - Bc));
const distanceOfD = Math.abs(Jr - Dr) + Math.abs(Jc - Dc);

//// 올 미리 초기화하는 아이디어 굿
let result = 'tie';
if (distanceOfB < distanceOfD) {
  result = 'bessie';
} else if (distanceOfB > distanceOfD) {
  result = 'daisy';
}
console.log(result);
//// ㄲㄲ