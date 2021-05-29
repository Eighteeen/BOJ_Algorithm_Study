const fs = require('fs');
const input = (
  process.platform === 'linux'
    ? fs.readFileSync('/dev/stdin').toString().trim()
    : `15
20 20 33 98 15 6 4 1 1 1 2 3 6 14`
).split('\n');

//// 전체적으로 무난합니다.
const company_stock = input[1].split(' ').map((value) => parseInt(value));

let assets = {
  cash: parseInt(input[0]),
  stock: 0,
};

//// 무엇을 반환하는지 함수명으로 어느정도 나타나 있으면 좋을 것 같아요.
//~ 오호 좋은 조언이네요! 변경하였습니다.
const getAssetsBNPStrategy = (assets, company_stock) => {
  for (let item of company_stock) {
    if (assets.cash < item) continue;

    assets = {
      cash: assets.cash % item,
      stock: assets.stock + parseInt(assets.cash / item),
    };
  }
  return assets;
};

//// 요 줄 지워도 똑같아요!(...복사 제거)
// => 알려주셔서 감사합니다~
const getAssetsTimingStrategy = (assets, company_stock) => {
  for (let i = 0; i < company_stock.length - 3; i++) {
    if (company_stock[i] > company_stock[i + 1] && company_stock[i + 1] > company_stock[i + 2]) {
      assets = {
        stock: assets.stock + parseInt(assets.cash / company_stock[i + 3]),
        cash: assets.cash % company_stock[i + 3],
      };
    } else if (company_stock[i] < company_stock[i + 1] && company_stock[i + 1] < company_stock[i + 2]) {
      assets = {
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

let junhyun_assets = calcAssets(getAssetsBNPStrategy(assets, company_stock), company_stock[13]);
let sungmin_assets = calcAssets(getAssetsTimingStrategy(assets, company_stock), company_stock[13]);

console.log(getResults(junhyun_assets, sungmin_assets));
