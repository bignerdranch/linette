Linette
============

[![Build Status](https://travis-ci.org/bignerdranch/linette.svg?branch=master)](https://travis-ci.org/bignerdranch/linette)

Custom Lint checks for Android development.

Setup
------------

Check out the repository to your local machine via:

`git clone https://github.com/bignerdranch/linette.git`

Then, from a terminal, run the following command in the Linette project:

`./gradlew clean build test install`

Now the custom Lint checks have been built, packaged, and installed to your `~/.android/lint` folder.

Verify
------------
To check that the custom Lint checks are now available, run the following commands:

`lint --show <issue-id>`

For example:

`lint --show Enum`

`lint --show MinSdk`

`lint --show Todo`

And to actually run the Lint checks, try the following command from a project of your choice:

`./gradlew lint`

Alternatively, you can just use Android Studio's `Analyze->Inspect Code...` menu option.

License
-------

    Copyright 2015 Big Nerd Ranch

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.