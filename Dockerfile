FROM openjdk
LABEL authors="raghayev"

COPY ./build/libs/ms17-1.0.jar /app/
WORKDIR /app/

ENTRYPOINT ["java"]
CMD ["-jar","/app/ms17-1.0.jar"]