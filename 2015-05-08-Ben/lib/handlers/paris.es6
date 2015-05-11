'use strict';

export default function(req, res, next) {
  if (!req.query.q.match(/which city is the Eiffel tower in/)) {
    return next();
  }
  res.send('Paris');
}
