# boot-cucumber

[![Clojars Project](https://img.shields.io/clojars/v/vonrosen/boot-cucumber.svg)](https://clojars.org/vonrosen/boot-cucumber)

[Boot] task for running cucumber tests called ```cukes```.

## Usage

Add `boot-cucumber` to your `build.boot` dependencies and `require` the
namespace:

```clj
(set-env! :dependencies '[[vonrosen/boot-cucumber "X.Y.Z" :scope "test"]])
(require '[vonrosen.boot-cucumber :refer :all])
```

By default, boot-cucumber will look for feature files in a ```features``` directory under the root of your project. By default, it will look for glue code under ```features/step_definitions```. To customize these default locations use the ```-f``` flag to pass a different feature file location and the ```-g``` flag to pass a different glue code location.

To invoke from the command line do
```
boot cukes
```

See the [examples] directory for usage examples.

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[Boot]: https://github.com/boot-clj/boot
[examples]: https://github.com/vonrosen/boot-cucumber/tree/master/examples
