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

//// 일단 다 입력받은 뒤 유일한 애만 걸러내는 방식이네요. 무난하고 좋은 것 같습니다
for (let i = 0; i < 3; i++) {
  const location = input().split(` `);
  dot_list.x.push(parseInt(location[0]));
  dot_list.y.push(parseInt(location[1]));
}

//// 제한 사항을 잘 이용한 풀이네요. 깔끔~
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
