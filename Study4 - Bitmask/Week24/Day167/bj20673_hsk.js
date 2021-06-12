const fs = require('fs');
let [p, q] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `15
12`
)
  .split('\n')
  .map((item) => parseInt(item));

if (p <= 50 && q <= 10) console.log('White');
else if (q <= 30) console.log('Yellow');
else console.log('Red');
