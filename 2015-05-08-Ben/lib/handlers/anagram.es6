// bcbc9ca0: which of the following is an anagram of "dictionary": abdication, indicatory, butterfly, incendiary

'use strict';

import _ from 'lodash';

export default function(req, res, next) {
  if (!req.query.q.match(/anagram of/)) {
    return next();
  }

  const word = req.query.q.match(/anagram of "([^"]+)"/)[1];
  const words = req.query.q.split('": ')[1].split(', ');

  const counts = words.map(getChars);
  const result = counts.reduce((agg, count, i) => {
    if (_.isEqual(count, getChars(word))) {
      agg.push(words[i]);
    }
    return agg;
  }, []);

  res.send(result.join(', '));
}


function getChars(word) {
  return word.split('').reduce((chars, c) => {
    if (c in chars) {
      chars[c]++;
    } else {
      chars[c] = 0;
    }
    return chars;
  }, {});
}
