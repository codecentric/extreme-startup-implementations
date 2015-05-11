'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/what colour is a banana/)) {
    return next();
  }
  res.send('yellow');
}
