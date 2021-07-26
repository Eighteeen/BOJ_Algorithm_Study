const fs = require('fs');
const stdin = (process.platform === 'linux' ? fs.readFileSync('/dev/stdin').toString().trim() : ``).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const skipInput = (lines) => {
  for (let i = 0; i < lines; i++) {
    input();
  }
};
