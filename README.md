SCROLLAndroid
=============
*SCala ROLes Language* "Hello World!" example project for Android.

**Edit and run:**

1. Setup your development environment for Android and Scala. [This][android] might help.

2. Clone this repo.

3. Run SBT and run ```gen-idea```if you are using Intellij IDE <= 13 (to config see [here][sbt-gen-idea]). This is not required anymore since Intellij 14. Just use the built-in import SBT project functionality.

4. Run SBT and run ```eclipse``` if you are using the Eclipse Scala IDE. (to config see [here][gen-eclipse])

5. Run ```sbt android:run``` to package and start the app on your device (this could take a while).

[sbt-gen-idea]: https://github.com/mpeltonen/sbt-idea
[gen-eclipse]: https://github.com/typesafehub/sbteclipse
[android]: http://scala-ide.org/docs/tutorials/androiddevelopment/
