# Demo Spring Boot App

Demo of a Spring Boot application with Kotlin and JDK 15. Application is Dockerized and ready to be deployed on Kubernetes with Helm.

## How-to

Application can be managed via a ``run.sh`` script:

- ``./run.sh build`` to build the application with Gradle and then build a Docker image
- ``./run.sh start`` to install/upgrade the application with helm on Kubernetes
- ``./run.sh stop`` to uninstall the application
- ``./run.sh remove`` to uninstall application and delete docker images

### Access pod

After successful installation, `kubectl port-forward <pod_name> 8080:8080` and browse to localhost:8080 should show a "Hello World" message 