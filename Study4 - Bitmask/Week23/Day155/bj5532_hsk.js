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

//// js는 나눠떨어지지 않으면 소수점이 붙어서 나오니까
//// 고걸 이용해서 소수점 첫자리에서 올림했으면 더 깔끔했을 것 같아요!
const solve_korean = parseInt(A / C) + (A % C !== 0 ? 1 : 0);
const solve_math = parseInt(B / D) + (B % D !== 0 ? 1 : 0);

const playing = L - Math.max(solve_math, solve_korean);
console.log(playing);
