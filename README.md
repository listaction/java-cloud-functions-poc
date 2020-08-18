# POC
Simple poc of cloud functions using function invoker and streaming http adapter

# Pre-requisities
```
 golang 1.13+
 mvn 3.6+
 java8+
```

# Instructions
This is a generic function invoker that loads this repo using a class loader and calls the cloud functions using supplier / consumer interfaces in Java 8
This invoker interfaces with the outside world via grpc and by default listens on a wellknown port. 
```
cd ..
git clone https://github.com/listaction/java-function-invoker.git
cd java-function-invoker
mvn clean install
#(Note: some integration tests fail)
```
This is an adapter that receives the request over http and communicates with invoker over grpc which ultimately calls the respective function.
```
cd ..
git clone https://github.com/listaction/streaming-http-adapter.git
cd streaming-http-adapter
make 
```
  
```
cd ../java-cloud-functions-poc
mvn clean install
cd ../streaming-http-adapter
```

1. This step spawns a java process and instantiates java-function-invoker class and its dependencies.  
2. java-function-invoker is a grpc server which by default listens on 8081.
3. java-function-invoker reads spring.cloud-function.location property and loads uppercase-0.0.2.jar using class loader.
4. streaming-http-adapter listens on 8080 by default and is a http server. 
```
./streaming-http-adapter java  -Dspring.cloud.function.location=../java-cloud-functions-poc/target/uppercase-0.0.2.jar -jar ../java-function-invoker/target/java-function-invoker-0.2.1-SNAPSHOT.jar
```

Testing
```
curl http://127.0.0.1:8080/ -H 'Content-Type: text/plain' -d RandomString
```