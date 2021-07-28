# BiomeProviderApi
This is a biome data generator for minecraft forge mods.

Usage in custom mods:

First, add this to your resources:
```gradle
maven {
  url = uri("https://maven.pkg.github.com/hackcoder1000/biomeproviderapi")
  credentials {
    username = property('gpr.username')
    password = property('gpr.token')
  }
}
```

Then, in your dependencies:

```gradle
compileOnly fg.deobf("net.legiblesleet827:biomeapi:${project.biomeapi_version}") {
  exclude module: "forge"
}

runtimeOnly fg.deobf("net.legiblesleet827:biomeapi:${project.biomeapi_version}") {
  exclude module: "forge"
}
```

If all goes well, you can use the biome api data gen class in your own mod!