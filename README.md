```shell
docker build -t old-java -f jdk8u92/.
docker run --rm --memory=1g -ti old-java

docker build . -t new-java -f jdk8-latest/.
docker run --rm --memory=1g -ti new-java
```