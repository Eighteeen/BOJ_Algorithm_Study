const fs = require('fs');
const input = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `20
25
30
6
8`
).split('\n');

const [L, A, B, C, D] = input.map((value) => Number(value));

const solve_korean = parseInt(A / C) + (A % C !== 0 ? 1 : 0);
const solve_math = parseInt(B / D) + (B % D !== 0 ? 1 : 0);

const playing = L - Math.max(solve_math, solve_korean);
console.log(playing);
