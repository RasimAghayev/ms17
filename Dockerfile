FROM openjdk

RUN echo "Hello"
COPY ./build/libs/ms17-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/

# test
# test
ENTRYPOINT [ "java" ]
CMD [ "-jar","/app/ms17-0.0.1-SNAPSHOT.jar" ]