const fs = require('fs');
const num = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `314`).split('');

let result = '';
num.forEach((char, idx) => {
  const octal = parseInt(char, 8);
  let binary = octal.toString(2);

  while (idx != 0 && binary.length < 3) {
    binary = '0' + binary;
  }
  result += binary;
});

console.log(result);
//// ㄲㄲ : 22