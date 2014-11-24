## Fluent-http on App Engine Managed VMs

This is a demonstration project for App Engine Managed VMs.
It leverages App Engine Docker's support to run a web app
with Java 8. It uses [fluent-http] web stack.

This application uses this [base image](https://github.com/dgageot/docker-fluent).

## To use managed VMS

1. [Download the Google Cloud SDK](https://cloud.google.com/sdk/) and create a billing-enabled project.

2. Enable billing on your project by visiting [https://cloud.google.com/console/project/apps~<app_id>](https://cloud.google.com/console/project/apps~<app_id>), 
where <app_id> is the ID of your project. Click on **Settings** in the left menu and then **enable billing**.

3. Set the default project to your project's ID using the config command:

  ```bash
  gcloud config set project <project ID>
  ```

4. The first time you use the Cloud SDK, you may be asked to run:

  ```bash
  gcloud auth login
  ```

5. Install the app engine gcloud component by invoking the following:

  ```bash
  gcloud components update app
  ```
  
6. Install Docker

 + Mac OS X: http://docs.docker.io/installation/mac/
 + Windows: http://docs.docker.io/installation/windows/
 + Other operating systems: https://docs.docker.com/installation/
 
7. Configure boot2docker

  Boot2docker runs with 2048Mb of memory by default. It has to be increased to 4096 at least:

  ```bash
  boot2docker stop
  VBoxManage modifyvm "boot2docker-vm" --memory "4096"
  boot2docker up
  ```

8. Download the base App Engine docker images for all runtimes:

  ```bash
  gcloud preview app setup-managed-vms
  ```
  
9. Run the application locally:

  ```bash
  gcloud preview app run .
  ```
  
10. Deploy the application:

  ```bash
  gcloud preview app deploy .
  ```
