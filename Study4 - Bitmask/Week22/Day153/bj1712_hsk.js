const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `3 2 1`).split(' ');

const fixed_cost = parseInt(input[0]);
const variable_cost = parseInt(input[1]);
const laptop_cost = parseInt(input[2]);

let break_even_point = parseInt(fixed_cost / (laptop_cost - variable_cost) + 1);
if (laptop_cost <= variable_cost) console.log('-1');
else console.log(break_even_point);
