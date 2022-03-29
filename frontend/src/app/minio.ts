export class UpdateResponse {

  constructor(public objectId: string) {
  }
}

export class PresignedObjectUrlResponse {
  constructor(
    public objectId: string,
    public url: string,
    public bucket: string) {
  }
}
