# Quarkus Morphium Showcase

> **This repository has moved.** The showcase is a module of the Morphium repository since 7.0 and is maintained there:
> **[sboesebeck/morphium: `quarkus-morphium-showcase`](https://github.com/sboesebeck/morphium/tree/release/7.x/quarkus-morphium-showcase)**
>
> It uses the Morphium and Quarkus versions of that checkout instead of a release, so it no longer needs a second pass for every change. The history of this repository is part of the module. Please open issues and pull requests there.
>
> This repository is archived and kept only so that old links keep working. The last state, built against Morphium 6.3.1, is the tag `final-6.3.1`.

```bash
git clone https://github.com/sboesebeck/morphium.git
cd morphium
mvn -Pshowcase -pl quarkus-morphium-showcase -am install -DskipTests
mvn -Pshowcase -pl quarkus-morphium-showcase quarkus:dev
```

License: Apache 2.0, see [LICENSE](LICENSE).
