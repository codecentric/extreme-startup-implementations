# Extreme Startup Coding Dojo

For this Clojure-based implementation I used the [modularity.org](www.modularity.org) project scaffolding, which is total overkill for this dojo, nonetheless let's you set-up a [reloaded](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded), [component](https://github.com/stuartsierra/component)-based Clojure system using a single command:
```
lein new modular extreme-modular bidi-hello-world
```

# Workflow

For the dojo I started a REPL inside [Cursive](https://cursiveclojure.com/) and used it to quickly test new solutions inside the REPL, "deploying" added code by invoking `(dev/reset)` (see the [reloaded-blogpost](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded) for the details). This setup and the language (especially lazy seqs, regular expression literals and higher order functions) is very well suited for the dojo, however one should probably add integration tests and most importantly try to breath and think while being bombarded by all those requests and different questions :)

