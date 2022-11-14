FROM openjdk:8u342-jdk

COPY /src/main/java/*.java /src/

RUN mkdir /app && \
    javac /src/*.java -d /app

CMD ["sh", "-c", "java -version && java -cp /app OutOfMemoryExamples"]
