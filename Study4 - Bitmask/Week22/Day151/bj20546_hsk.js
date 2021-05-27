const fs = require('fs');
const input = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `15
20 20 33 98 15 6 4 1 1 1 2 3 6 14`
).split('\n');

const company_stock = input[1].split(' ').map((value) => parseInt(value));

let assets = {
  cash: parseInt(input[0]),
  stock: 0,
};

const bnpStrategy = (assets, company_stock) => {
  for (let item of company_stock) {
    if (assets.cash < item) continue;

    assets = {
      ...assets,
      cash: assets.cash % item,
      stock: assets.stock + parseInt(assets.cash / item),
    };
  }
  return assets;
};

const timingStrategy = (assets, company_stock) => {
  for (let i = 0; i < company_stock.length - 3; i++) {
    if (company_stock[i] > company_stock[i + 1] && company_stock[i + 1] > company_stock[i + 2]) {
      assets = {
        ...assets,
        stock: assets.stock + parseInt(assets.cash / company_stock[i + 3]),
        cash: assets.cash % company_stock[i + 3],
      };
    } else if (company_stock[i] < company_stock[i + 1] && company_stock[i + 1] < company_stock[i + 2]) {
      assets = {
        ...assets,
        cash: assets.cash + assets.stock * company_stock[i + 3],
        stock: 0,
      };
    }
  }
  return assets;
};

const calcAssets = (assets, stock) => {
  return assets.stock * stock + assets.cash;
};

const getResults = (bnp, timing) => {
  if (bnp > timing) return 'BNP';
  else if (bnp < timing) return 'TIMING';
  return 'SAMESAME';
};

let junhyun_assets = calcAssets(bnpStrategy(assets, company_stock), company_stock[13]);
let sungmin_assets = calcAssets(timingStrategy(assets, company_stock), company_stock[13]);

console.log(getResults(junhyun_assets, sungmin_assets));
