'use strict';


export default function(req, res, next) {
  if (!req.query.q.match(/what is the english scrabble score of/)) {
    return next();
  }

  const word = req.query.q.match(/scrabble score of ([a-z]+)/i)[1];
  res.send(getScrabbleScore(word) + '');
}


function getScrabbleScore(word) {
  word = word || '';

  let currentScore = 0;
  let characters = word.split('');

  let totalScores = characters.map(function(letter) {
    return getScoreForLetter(letter.toLowerCase());
  });

  currentScore = totalScores.reduce(function(sum, score) {
    return sum + score;
  }, 0);

  return currentScore;
}

function getScoreForLetter(letter) {
  return {
    a: 1,
    e: 1,
    i: 1,
    o: 1,
    u: 1,
    l: 1,
    n: 1,
    r: 1,
    s: 1,
    t: 1,
    d: 2,
    g: 2,
    b: 3,
    c: 3,
    m: 3,
    p: 3,
    f: 4,
    h: 4,
    v: 4,
    w: 4,
    y: 4,
    k: 5,
    j: 8,
    x: 8,
    q: 10,
    z: 10
  }[letter];
}
