## Angular upload file using [MinIO](https://min.io/)

Example of angular upload/download files using MinIO, you can run locally

```bash
docker-compose --env-file .env.example up -d
```

then run angular project

```bash
npm start
```

## Topologies 

1. Upload document

```mermaid
sequenceDiagram
    autonumber
    actor client AS Client
    participant backend AS Springboot
    participant minio AS MinIO S3 Storage
    participant postgres AS PostgreSQL

    activate backend
    activate minio
    activate postgres

    client->backend: Upload image
    Note over client,backend: Sending via Rest API form encripted
    backend->minio: Sending image
    Note over backend,minio: Sending files via S2 protocol
    minio-->backend: Response object id
    backend->postgres: Save object id
    postgres-->backend: Object id saved!

```

2.Presigned image URL string to download

```mermaid
sequenceDiagram
    autonumber
    actor client AS Client
    participant backend AS Springboot
    participant minio AS MinIO S3 Storage
    participant postgres AS PostgreSQL
```

## Supported web browsers

| Browser | Version |
| :---    | :---    |
|Google Chrome	|49.0+|
|Mozilla Firefox	|45.0+|
|Opera	|36.0+|
|Microsoft Edge	|12.0+|
|Windows Internet Explorer	|N/A|
|Apple Safari	|9.0+|
|Android Browser	|76.0+|
|UC Browser	|12.12+|
|Samsung Internet	|5.0+|


ref: 
- https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/browsers-supported.html
