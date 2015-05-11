'use strict';

export default function(req, res, next) {
  if (req.query.q.match(/what is your name/)) {
    res.send('bripkens');
  } else {
    next();
  }
}
