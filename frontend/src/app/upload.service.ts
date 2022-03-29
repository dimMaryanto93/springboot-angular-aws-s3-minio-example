import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../environments/environment";
import {PresignedObjectUrlResponse, UpdateResponse} from "./minio";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private _http: HttpClient) {
  }

  public upload(file: File, folder: string) {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('folder', folder);

    return this._http.post<UpdateResponse>(
      `${environment.upload}/api/v1/minio/upload`,
      formData,
      {
        responseType: "json",
        observe: 'body'
      });
  }

  public getUrl(path: string) {
    const data = {
      objectId: path,
      duration: 1,
      unit: 'HOURS'
    }
    return this._http.post<PresignedObjectUrlResponse>(
      `${environment.upload}/api/v1/minio/preview`,
      data
    )
  }
}
