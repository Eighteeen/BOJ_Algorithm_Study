const fs = require('fs');
const whiteChessPieces = (
  process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `2 1 2 1 2 1`
)
  .split(' ')
  .map(Number);

const CHESS = [1, 1, 2, 2, 2, 8];
const result = whiteChessPieces.map((piece, idx) => CHESS[idx] - piece);
console.log(...result);
