![codelib](https://notcoded.needs.rest/r/codelib_banner.png)

yeahh!! this is the fabric library mod that i *sometimes* use in my projects.

did i mention it has **0**, yes **zero** dependencies?

## Features
* read the javadocs somewhere (todo: implement javadocs github pages)
* more coming soon

## How to use

Add the following to your `gradle.properties`
```properties
# https://github.com/not-coded/codelib
codelib_version=...+minecraft_version

# Example
codelib_version=2.0.0+1.20
```

And `build.gradle`
```groovy
repositories {
    maven {
        name = "Modrinth"
        url = "https://api.modrinth.com/maven"
        content {
            includeGroup "maven.modrinth"
        }
    }
}


dependencies {
    modApi "maven.modrinth:codelib:${project.codelib_version}"

    // you can include it directly if you want you don't want your users to download it
    include "maven.modrinth:codelib:${project.codelib_version}"
}
```
