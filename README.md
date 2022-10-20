Jib builds optimized Docker and OCI images for your Java applications without a Docker daemon. 
This is a hello world example using Scala and SBT.  

See:
- [jib](https://github.com/GoogleContainerTools/jib)
- sbt plugin [sbt-jib](https://github.com/schmitch/sbt-jib)

## To build the image:

In build.sbt replace REPLACE-WITH-YOUR-DOCKER-HUB-ORGANIZATION with your docker hub organization or username.

Build you image:
```
> sbt compile jibImageBuild
```

Change the following command to run your newly build image:
```
>  docker run --rm  --platform linux/arm64 REPLACE-WITH-YOUR-DOCKER-HUB-ORGANIZATION/jib-hello-world:0.0.1-SNAPSHOT world
Hello world
```