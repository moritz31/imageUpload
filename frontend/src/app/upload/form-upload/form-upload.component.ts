import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import { UploadFileService } from '../upload-file.service';

@Component({
  selector: 'form-upload',
  templateUrl: './form-upload.component.html',
  styleUrls: ['./form-upload.component.css']
})
export class FormUploadComponent implements OnInit {

  selectedFiles: FileList
  currentFileUpload: File
  tags: String

  constructor(private uploadService: UploadFileService) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    console.log(event.target);
  }

  inputTag(event) {
    this.tags = event.target.value;
  }

  upload() {

    Array.from(this.selectedFiles).forEach(file => {
      this.uploadService.pushFileToStorage(file,this.tags).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
        } else if (event instanceof HttpResponse) {
          console.log('File is completely uploaded!');
          window.location.reload();
        }
      })
    });

    this.selectedFiles = undefined
  }
}
