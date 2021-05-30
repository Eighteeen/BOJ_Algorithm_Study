const fs = require('fs');
const input = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `0/0/1`).split('/');

const K = parseInt(input[0]);
const D = parseInt(input[1]);
const A = parseInt(input[2]);

const isReal = (K, D, A) => {
  if (K + A < D || D === 0) return false;
  return true;
};

console.log(isReal(K, D, A) ? 'gosu' : 'hasu');
