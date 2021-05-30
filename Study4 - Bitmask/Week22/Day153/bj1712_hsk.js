const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3 2 1`).split(' ');

const fixed_cost = parseInt(input[0]);
const variable_cost = parseInt(input[1]);
const laptop_cost = parseInt(input[2]);

//// laptop_cost <= variable_cost 일때는 break_even_point를 계산할 필요가 없으니 else 부분에 계산 부분을 넣었으면 더 깔끔할 것 같습니다.
let break_even_point = parseInt(fixed_cost / (laptop_cost - variable_cost) + 1);
if (laptop_cost <= variable_cost) console.log('-1');
else console.log(break_even_point);
