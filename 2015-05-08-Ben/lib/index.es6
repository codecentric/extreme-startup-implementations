'use strict';

import app from './app';

const server = app.listen(process.argv[2] || 3000, () => {
  const host = server.address().address;
  const port = server.address().port;
  console.log('Example app listening at http://%s:%s', host, port);
});
