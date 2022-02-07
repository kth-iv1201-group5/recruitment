###########
#  Maven  #
###########################################################################
# In this stage the docker file will copy 'pom.xml' and 'src' directory.  #
# 'mvn clean package' will compile the code and create a jar file.        #
###########################################################################
FROM maven:3.8.4-openjdk-8-slim AS maven
WORKDIR application
COPY pom.xml .
COPY src ./src
ARG PORT
RUN mvn -DPORT=$PORT -Dmaven.test.skip clean package

###########
# Builder #
###########################################################################
# In this stage we fetch the '*.jar' file from 'maven-stage' and extract  #
# the content of the JAR file.                                            #
###########################################################################
FROM openjdk:8-jdk-alpine AS builder
WORKDIR application
COPY --from=maven application/target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

###########
#  Main   #
###########################################################################
# In this stage we fetch the extracted content from the 'builder-stage'   #
# and make it runnable application                                        #
###########################################################################
FROM openjdk:8-jdk-alpine
WORKDIR application
COPY --from=builder application/application/ ./
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]



