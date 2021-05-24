const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `30 20
10 10
10 20`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let dot_list = {
  x: [],
  y: [],
};

for (let i = 0; i < 3; i++) {
  const location = input().split(` `);
  dot_list.x.push(parseInt(location[0]));
  dot_list.y.push(parseInt(location[1]));
}

dot_list.x.map((item, index) => {
  if (dot_list.x.indexOf(item) !== index) {
    dot_list.x.splice(index, 1);
    dot_list.x.splice(dot_list.x.indexOf(item), 1);
  }
});

dot_list.y.map((item, index) => {
  if (dot_list.y.indexOf(item) !== index) {
    dot_list.y.splice(index, 1);
    dot_list.y.splice(dot_list.y.indexOf(item), 1);
  }
});

console.log(`${dot_list.x} ${dot_list.y}`);
