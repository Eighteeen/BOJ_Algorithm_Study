const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
2 KIWI BANANA
2 KIWI APPLE
2 APPLE APPLE
3 APPLE BANANA KIWI`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const makeAntTrieAsDictionary = (pathByPreyInfo) => {
  let curTrie = antTrie;

  pathByPreyInfo.map((node) => {
    if (!curTrie.has(node)) curTrie.set(node, new Map());
    curTrie = curTrie.get(node);
  });
};

const printAntTrie = (trie, depth = 0) => {
  const prefix = '--'.repeat(depth);
  const sortedTrie = [...trie.entries()].sort();

  let visualizedStr = [];
  for (let [keyOfChildTrie, valueOfChildTrie] of sortedTrie) {
    visualizedStr.push(prefix + keyOfChildTrie);

    if (valueOfChildTrie.size === 0) continue;
    visualizedStr.push(printAntTrie(valueOfChildTrie, depth + 1));
  }

  return visualizedStr.join('\n');
};

const N = parseInt(input());
const antTrie = new Map();

for (let i = 0; i < N; i++) {
  const [K, ...pathByPreyInfo] = input().split(' ');
  makeAntTrieAsDictionary(pathByPreyInfo);
}

const visualizedAntStructure = printAntTrie(antTrie);
console.log(visualizedAntStructure);
