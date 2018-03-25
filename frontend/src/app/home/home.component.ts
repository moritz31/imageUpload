import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import { UploadFileService } from '../upload/upload-file.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  showFile = false
  fileUploads: Observable<string[]>
  image:string;

  constructor(private app: AppService, private http: HttpClient, private uploadService: UploadFileService) {
    this.fileUploads = this.uploadService.getFiles();
  }

  authenticated() { return this.app.authenticated; }

  showFiles(enable: boolean) {
    this.showFile = enable

    if (enable) {
      this.fileUploads = this.uploadService.getFiles();
    }
  }

  ngOnInit() {

  }
}
