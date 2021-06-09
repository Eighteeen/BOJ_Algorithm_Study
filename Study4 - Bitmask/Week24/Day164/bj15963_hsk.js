const fs = require('fs');
let [N, M] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1 0`)
  .split(' ')
  .map((item) => parseInt(item));

//// js === 으로 왕 깔끔
if (N === M) console.log(1);
else console.log(0);
