const fs = require('fs');
const board = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `........
..F.....
.....F..
.....F..
........
........
.......F
.F......`
)
  .split('\n')
  .map((value) => value.split(''));

let horseCntOnWhite = 0;
for (let i = 0; i < 8; i++) {
  for (let j = 0; j < 8; j++) {
    if ((i + j) % 2 === 0 && board[i][j] === 'F') horseCntOnWhite++;
  }
}

console.log(horseCntOnWhite);
//// ㄲㄲ! : 22