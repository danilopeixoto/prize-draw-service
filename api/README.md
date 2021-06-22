# Prize Draw API

A prize draw API.

## Prerequisites

* [OpenJDK (>=11)](https://openjdk.java.net)

## Installation

Build and run dependencies:

```
docker-compose -f ../docker-compose.development.yml up -d
```

Run service:

```
gradlew bootRun --args='--spring.profiles.active=development'
```

## Copyright and license

Copyright (c) 2021, Mercado Libre, Inc. All rights reserved.

Project developed under a [BSD-3-Clause license](LICENSE.md).
