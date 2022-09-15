![GitHub Actions](https://github.com/adessoTurkey/android-deneysiz/workflows/Static%20Analysis%20Checks/badge.svg)
# About

This project the under development version of [deneysiz] application by [adesso Turkey].
Please visit [deneyehayir.org] for more information.

[deneysiz]: https://play.google.com/store/apps/details?id=com.deneyehayir.deneysiz
[deneyehayir.org]: https://www.deneyehayir.org/
[adesso Turkey]: https://www.adesso.com.tr/

## Development

### Code style [*](https://github.com/VMadalin/kotlin-sample-app)

To maintain the style and quality of the code, are used the bellow static analysis tools. All of them use properly configuration and you find them in the project root directory `config/{toolName}`.

| Tools                             | Config file                                 | Check command                | Fix command               |
|-----------------------------------|--------------------------------------------:|------------------------------|---------------------------|
| [detekt][detekt]                  | [detekt.yml](/config/detekt/detekt.yml)     | `./gradlew detekt`           | -                         |
| [ktlint][ktlint]                  | -                                           | `./gradlew ktlint`           | `./gradlew ktlintFormat`  |
| [spotless][spotless]              | -                                           | `./gradlew spotlessCheck`    | `./gradlew spotlessApply` |
| [lint][lint]                      | [lint.xml](/config/lint/lint.xml)           | `./gradlew lint`             | -                         |

All these tools are integrated in [pre-commit git hook], in order ensure that all static analysis and
tests passes before you can commit your changes. To skip them for specific commit add this option at your git command:

```properties
git commit --no-verify
```

It's highly recommended to fix broken code styles. There is [a gradle task](/build.gradle.kts#L23) which execute `ktlintFormat` and `spotlessApply` for you:

```properties
./gradlew reformat
```

[pre-commit git hook]: https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks
[detekt]: https://github.com/arturbosch/detekt
[ktlint]: https://github.com/pinterest/ktlint
[spotless]: https://github.com/diffplug/spotless
[lint]: https://developer.android.com/studio/write/lint

## Pull Requests

[Gitflow] is considered as workflow in the project. All feature branches should be based on `develop` branch.
Before submitting a pull request, please consider:
1. Write a [good commit message].
2. Update [README](README.md) with any changes are needed.
3. Source branch should be ahead 1 commit than target branch.
4. Make sure all gradle tasks are passing.

[Gitflow]: https://nvie.com/posts/a-successful-git-branching-model/
[good commit message]: https://chris.beams.io/posts/git-commit/

## Architecture (TBD)

- Single Activity
- MVVM Pattern

**Jetpack Compose:** Declarative toolkit for building native UI

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase

**UseCase:** Contains all business rules and they written in the manner of single responsibility principle

**Repository:** Single source of data. Responsible to get data from one or more data sources

## Tech Stack

#### Dependencies (TBD)

- **[Jetpack Compose](https://developer.android.com/jetpack/compose/documentation):** Modern toolkit for building native Android UI.
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Holds UI data across configuration changes
- **[Dagger Hilt](https://github.com/google/dagger):** Dependency injector
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines):** Asynchronous programming
- **[Retrofit](https://github.com/square/retrofit):** Type safe HTTP client
- **[Kotlinx.Serialization](https://github.com/Kotlin/kotlinx.serialization):** JSON serializer/deserializer

## License

```
Copyright 2021 adesso Turkey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

