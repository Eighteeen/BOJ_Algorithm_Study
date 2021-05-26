const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

//// ㄲㄲ
const input = [];

rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  const x = parseInt(input[0]);
  const y = parseInt(input[1]);

  let quaerant = 1;

  if (x < 0) {
    if (y > 0) quaerant = 2;
    else quaerant = 3;
  } else {
    if (y < 0) quaerant = 4;
  }

  console.log(quaerant);

  process.exit();
});
