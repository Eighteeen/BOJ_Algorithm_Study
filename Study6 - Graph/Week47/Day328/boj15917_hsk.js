const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `10
1
2
7
4
14
32
33
34
35
36`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const CanExpressedAsPower = (num) => {
  if ((num & -num) === num) return true;
  return false;
};

const Q = Number(input());
const result = [];
for (let i = 0; i < Q; i++) {
  const a = Number(input());
  result.push(CanExpressedAsPower(a) ? 1 : 0);
}
console.log(result.join('\n'));
