const fs = require('fs');
let [N, M] = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : `1 0`)
  .split(' ')
  .map((item) => parseInt(item));

if (N === M) console.log(1);
else console.log(0);
