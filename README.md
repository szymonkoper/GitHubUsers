# GitHub Users

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


**GitHub Users** is an evaluation project. My task was to quickly build a small Android app to find GitHub users and show their details.


# Libraries used

* [ButterKnife](https://github.com/JakeWharton/butterknife) - Fields and methods binding for Android views
* [Dagger 2](https://github.com/google/dagger) - A fast dependency injector for Android and Java
* [Volley](https://github.com/google/volley) -  A networking library for Android


# What is missing

 - Testing (for example using Mockito and Espresso),
 - Offline cache for responses (from Volley),
 - Better informing about errors,
 - Taking care of GitHub API pagination (now it's max 100 items per response)


# License

Copyright 2017 Szymon Koper

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
