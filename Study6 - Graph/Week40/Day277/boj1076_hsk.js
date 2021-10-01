const fs = require('fs');
const [colorA, colorB, colorC] = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `white
white
white`
).split('\n');

const registers = { black: 0, brown: 1, red: 2, orange: 3, yellow: 4, green: 5, blue: 6, violet: 7, grey: 8, white: 9 };

console.log((registers[colorA] * 10 + registers[colorB]) * Math.pow(10, registers[colorC]));
//// js... : 22 Wow,,,