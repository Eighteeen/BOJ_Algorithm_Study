const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `(@&
?/--
/(\\
?
#`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const octopusObj = {
  '-': 0,
  '\\': 1,
  '(': 2,
  '@': 3,
  '?': 4,
  '>': 5,
  '&': 6,
  '%': 7,
  '/': -1,
};

let result = [];
while (1) {
  const codeOfOctopus = input();
  if (codeOfOctopus === '#') break;

  const lenOfCode = codeOfOctopus.split('').length;
  let decimalOfOctopusCode = 0;
  for (let i = 0; i < lenOfCode; i++) {
    decimalOfOctopusCode += octopusObj[codeOfOctopus[i]] * 8 ** (lenOfCode - i - 1);
  }

  result.push(decimalOfOctopusCode);
}

console.log(result.join('\n'));
//// ㄲㄲ