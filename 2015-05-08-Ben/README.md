# Extreme Startup Implementation by @bripkens
This solution shows what is possible with ECMAScript 6 (ES6) and Node.js. ES6 code is transpiled on application start via the `babel-node` CLI. Internally it registers a `require` extension to do the compilation.

The application also supports rolling restarts. It does so by proxying the application behind an nginx instance.

This solution greatly benefits from JavaScript's dynamic nature and especially `eval`. Actually, this solution does not use eval, but rather executes the mathematical operations in a restricted child process.

## Starting Instances
```
npm start -- {port}
```

## Executing Tests
```
npm test
```

## Starting multiple instances behind a load balancer
```
nginx -c nginx.conf -p ./nginx

# in separate terminals
npm start -- 3101
npm start -- 3102
```
