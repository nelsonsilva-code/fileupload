# Introduction
This repository serves as a base for any base repo that needs to upload files to AWS S3.

Currently consists of login with Spring Security (JWT) with localstack and AWS SDKv2.

## Commands and variables

### Start localstack
docker compose --file ./localstack/docker-compose.yml up -d

### Create S3
aws --endpoint-url=http://localhost:4566 s3 mb s3://fileupload

### Local env vars when running Spring
AWS_ACCESS_KEY_ID=verysecret;AWS_ENDPOINT=http://127.0.0.1:4566;AWS_REGION=eu-west-1;AWS_SECRET_ACCESS_KEY=verysecret;BUCKET=fileupload;JWT_SECRET=4e9b733de89b23dbcbbed1020dd18a88cc85bc37bc890789d4d0245081a79224