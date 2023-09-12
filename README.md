![codelib](https://notcoded.needs.rest/r/codelib_banner.png)

yeahh!! this is the fabric library mod that i *sometimes* use in my projects.

did i mention it has **0**, yes **zero** dependencies?

## Features
* read the [wiki](https://github.com/not-coded/codelib/wiki)
* more coming soon

## How to use

Add the following to your `gradle.properties`
```properties
# https://github.com/not-coded/codelib
codelib_version=...
```

And `build.gradle`
```groovy
repositories {
    maven {
        url "https://github.com/not-coded/notcoded-maven/raw/fabric/"
    }
}

dependencies {
    modApi "net.notcoded:codelib:${project.codelib_version}"

    // you can include it directly if you want you don't want your users to download it
    include "net.notcoded:codelib:${project.codelib_version}"
}
```