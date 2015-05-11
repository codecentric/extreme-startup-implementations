/*eslint-disable no-eval*/

'use strict';

import Sandbox from 'sandbox';

export default function(req, res, next) {
  const code = req.query.q
    .replace(/.*what is/, '')
    .replace(/plus/g, '+')
    .replace(/minus/g, '-')
    .replace(/multiplied by/g, '*')
    .replace(/divided by/g, '/')
    .replace(/(\d+) to the power of (\d+)/g, function(match, p1, p2) {
      return ' Math.pow(' + p1 + ', ' + p2 + ') ';
    });

  if (code.indexOf('\n') === -1) {
    new Sandbox().run(code, result => res.send(result.result));
  } else {
    next();
  }
}
