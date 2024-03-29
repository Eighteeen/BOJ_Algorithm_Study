const fs = require('fs');
const stdin = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `4
international
collegiate
programming
contest`
).split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

//// 무난 깔끔!
const isAbleToPronounce = (word) => {
  const visitedElementsInWord = new Array(word.length + 1).fill(false);
  const queue = [0];
  let queueCursor = 0;

  visitedElementsInWord[0] = true;
  while (queue.length > queueCursor) {
    const idx = queue[queueCursor++];

    if (idx === word.length) return true;

    let oneChar = word[idx];
    let twoChar = word[idx] + word[idx + 1];
    for (let i = 0; i < elements.length; i++) {
      if (oneChar === elements[i] && !visitedElementsInWord[idx + 1]) {
        visitedElementsInWord[idx + 1] = true;
        queue.push(idx + 1);
      }
      if (twoChar === elements[i] && !visitedElementsInWord[idx + 2]) {
        visitedElementsInWord[idx + 2] = true;
        queue.push(idx + 2);
      }
    }
  }

  return false;
};

const elements = [
  'h',
  'b',
  'c',
  'n',
  'o',
  'f',
  'p',
  's',
  'k',
  'v',
  'y',
  'i',
  'w',
  'u',
  'ba',
  'ca',
  'ga',
  'la',
  'na',
  'pa',
  'ra',
  'ta',
  'db',
  'nb',
  'pb',
  'rb',
  'sb',
  'tb',
  'yb',
  'ac',
  'sc',
  'tc',
  'cd',
  'gd',
  'md',
  'nd',
  'pd',
  'be',
  'ce',
  'fe',
  'ge',
  'he',
  'ne',
  're',
  'se',
  'te',
  'xe',
  'cf',
  'hf',
  'rf',
  'ag',
  'hg',
  'mg',
  'rg',
  'sg',
  'bh',
  'rh',
  'th',
  'bi',
  'li',
  'ni',
  'si',
  'ti',
  'bk',
  'al',
  'cl',
  'fl',
  'tl',
  'am',
  'cm',
  'fm',
  'pm',
  'sm',
  'tm',
  'cn',
  'in',
  'mn',
  'rn',
  'sn',
  'zn',
  'co',
  'ho',
  'mo',
  'no',
  'po',
  'np',
  'ar',
  'br',
  'cr',
  'er',
  'fr',
  'ir',
  'kr',
  'lr',
  'pr',
  'sr',
  'zr',
  'as',
  'cs',
  'ds',
  'es',
  'hs',
  'os',
  'at',
  'mt',
  'pt',
  'au',
  'cu',
  'eu',
  'lu',
  'pu',
  'ru',
  'lv',
  'dy',
];
const TEST_CASE = Number(input());

for (let i = 0; i < TEST_CASE; i++) {
  const word = input();
  console.log(isAbleToPronounce(word) ? 'YES' : 'NO');
}
