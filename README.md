# Bild och ord

A little game designed to help young children learn to read. The idea is to familiarise
the student with common words (in Swedish, as this game is implemented for a Swedish-language
learning platform). 

The student can drag-and-drop words to connect them with the correct illustration. In addition,
typing skills are fostered by having the student type in the words as well.

**Status:** Under development.

UI mockups:



## Developing

The application is written in Clojure and ClojureScript and is based on the [duct][]
web application template.

### Setup

When you first clone this repository, run:

```sh
lein setup
```

This will create files for local configuration, and prep your system
for the project.

### Environment

To begin developing, start with a REPL.

```sh
lein repl
```

Run `go` to initiate and start the system.

```clojure
user=> (go)
:started
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and reset the server. Changes to CSS or ClojureScript
files will be hot-loaded into the browser.

```clojure
user=> (reset)
:reloading (...)
:resumed
```

If you want to access a ClojureScript REPL, make sure that the site is loaded
in a browser and run:

```clojure
user=> (cljs-repl)
Waiting for browser connection... Connected.
To quit, type: :cljs/quit
nil
cljs.user=>
```

#### Frontend assets 

Stylesheets are written as SCSS files. To compile into CSS, run the following
in a background terminal screen:

```sh 
lein scss :dev auto
```



### Testing

Testing is fastest through the REPL, as you avoid environment startup
time.

```clojure
user=> (test)
...
```

But you can also run tests through Leiningen.

```sh
lein test
```

### Migrations

Migrations are handled by [ragtime] []. Migration files are stored in
the `resources/migrations` directory, and are applied in alphanumeric
order.

To update the database to the latest migration, open the REPL and run:

```clojure
user=> (migrate)
Applying 20150815144312-create-users
Applying 20150815145033-create-posts
```

To rollback the last migration, run:

```clojure
user=> (rollback)
Rolling back 20150815145033-create-posts
```

Note that the system needs to be setup with `(init)` or `(go)` before
migrations can be applied.

## Deploying

FIXME: steps to deploy

## License

Copyright © 2016 Johannes Staffans

This software is distributed under the [MIT license][].

[duct]: https://github.com/weavejester/duct
[MIT license]: https://opensource.org/licenses/MIT
[ragtime]: https://github.com/weavejester/ragtime

