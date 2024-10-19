## Prerequisites

1. **Docker**: Ensure Docker is installed and running.
2. **Kubectl**: Install Kubectl and configure it to communicate with Minikube.
3. **Minikube**: Start Minikube with the command:
   ```bash
   minikube start
   ```
## Steps to Deploy
### Build the Spring Boot Application
Navigate to the project directory and build the application using Maven:
```bash
cd D:\path\to\your-springboot-project
mvn clean package
```
### Build the Docker Image
Set the Docker environment to use Minikube's Docker daemon. Open a new Command Prompt or PowerShell window and run:
```bash
minikube docker-env
```
On executing above command you may see below message
```bash
SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://127.0.0.1:53545
SET DOCKER_CERT_PATH=C:\Users\madhu\.minikube\certs
SET MINIKUBE_ACTIVE_DOCKERD=minikube
REM To point your shell to minikube's docker-daemon, run:
REM @FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i
```
Execute last line on command 
```bash
@FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i
```
If all went well on executing below comand you should see minikube containers
```bash
docker container ls
```
Now build the image
```bash
docker build -t your-image-name:1.0.4 .
```
### Apply Configmap

```bash
kubectl apply -f ./configmap/configmap.yml
```
### Deploy the service
```bash
kubectl apply -f ./deployment/deployment.yml
```
### Create a Service
```bash
kubectl apply -f deployment/service.yml
```
### Access the Application
After deployment, access your Spring Boot application through the Minikube service using the following command:
```bash
minikube service your-service
```
This command will open your default web browser to the service URL.
append /app-config to the url. On browser you should see
```bash
Reading config property from:

Local application.yml: 20sec

Property from External file external-config/ext-config.yml: /url/for/whatsApp/search

Property1 from Configmap volume external-config/ext-config.yml: This is a value of property 1 read from config mounted as a volume

Property2 from Configmap volume external-config/ext-config.yml: This is a value of property 2 read from config mounted as a volume

Property from Configmap exposed as environment variable: This is an environment-specific message from ConfigMap
```
