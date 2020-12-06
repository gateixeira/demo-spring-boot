#!/bin/bash

build() {
    echo "Gradlew build - demo-spring-boot"
    ./gradlew build
    echo "Docker build - demo-spring-boot"
    eval $(minikube docker-env)
    docker build -t gateixeira/demo-spring-boot:latest .
}

stop() {
    echo "Helm uninstall - demo-spring-boot"
    helm uninstall demo-spring-boot
}

remove() {
    stop
    echo "Wait 15 for helm to uninstall application"
    sleep 15
    echo "Remove image - demo-spring-boot"
    if docker images | grep -q demo-spring-boot
    then
        docker rmi $(docker images | grep demo-spring-boot | awk "{print \$1}")
    fi

    echo "demo-spring-boot purged successfully."
}

start() {
    echo "Start -- Upgrade Kubernetes deployment with Helm - demo-spring-boot version $1"
    helm upgrade --install demo-spring-boot charts --values charts/values.yaml
}

echo "Set docker context to minikube"
eval $(minikube docker-env)

if [ "build" = $1 ]; then
    build $2
elif [ "start" = $1 ]; then
    start
elif [ "stop" = $1 ]; then
    stop
elif [ "remove" = $1 ]; then
    stop
    remove
elif [ "helm_upgrade" = $1 ]; then
    if [ -z "$2" ]; then
        echo "Missing version: run helm-upgrade <version>"
    else
        helm_upgrade $2
    fi
else
echo "Missing argument. (build <version> | stop | remove | helm_upgrade <version> )"
    exit 1
fi