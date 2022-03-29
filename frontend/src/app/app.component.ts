import {Component} from '@angular/core';
import {UploadService} from "./upload.service";
import {FormBuilder, Validators} from "@angular/forms";
import {PresignedObjectUrlResponse} from "./minio";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public images: string[] = [];

  form = this._formBuilder.group({
    objectId: this._formBuilder.control('', [Validators.required]),
    folder: this._formBuilder.control('images')
  })

  constructor(
    private _uploadService: UploadService,
    private _formBuilder: FormBuilder) {
  }

  onFileChanges(event: any) {
    let file = event.target?.files[0];
    this._uploadService.upload(file, 'images')
      .subscribe(resp => {
        console.log('response: ', resp);
        this.form.patchValue({
          objectId: resp.objectId
        })
      }, error => {
        console.error('response: ', error)
      });
  }

  upload(event: any) {
    let value = this.form.value;
    console.log('submit: ', value)
    this._uploadService.getUrl(value.objectId).subscribe((resp: PresignedObjectUrlResponse) => {
      this.images.push(resp.url)
    }, error => {
      console.log('error submit: ', error)
    })
  }
}
