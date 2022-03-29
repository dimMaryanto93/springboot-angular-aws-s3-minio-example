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
    participant client AS Client
    participant backend AS Springboot
    participant minio AS MinIO S3 Storage

    activate backend
    activate minio

    client->>backend: Upload image
    Note over client,backend: Sending via Rest API form encripted
    backend->>minio: Sending image
    Note over backend,minio: Sending files via S3 protocol
    minio-->>backend: Response object id
    backend-->>client: Received object id
    deactivate backend
```

2.Presigned image URL string to download

```mermaid
sequenceDiagram
    autonumber
    participant client AS Client
    participant backend AS Springboot
    participant minio AS MinIO S3 Storage

    activate backend
    activate minio

    client->>backend: Get image url by object id
    Note over client,backend: Sending via Rest API POST request
    backend->>minio: PresignedUrl(objectId)
    Note over backend,minio: Get image url using presignedUrl
    minio-->>backend: Response URL
    
    backend-->>client: Received image Presigned URL with timeout
    deactivate backend
    client->client: Render image from Response URL
```
