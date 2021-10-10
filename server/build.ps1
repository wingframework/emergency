.\mvnw.cmd  package
docker build . -t zhucediyici/demo:latest
docker push zhucediyici/demo:latest

