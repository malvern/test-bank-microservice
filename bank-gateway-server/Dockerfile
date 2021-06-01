# Build image for gateway server
FROM openjdk:latest
COPY target/*.jar  /data/apps/services/bank-gateway-server.jar
ENTRYPOINT ["java","-jar","/data/apps/services/bank-gateway-server.jar"]
EXPOSE 8010:8080
LABEL developer="malvern" \
      version="1.0" \
      application="bank-gateway-server"
