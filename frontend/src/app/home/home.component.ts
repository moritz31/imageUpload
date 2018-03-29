import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import { UploadFileService } from '../upload/upload-file.service';
import { Lightbox, LightboxConfig, LightboxEvent, LIGHTBOX_EVENT, IEvent, IAlbum } from 'angular2-lightbox';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private _albums: Array<IAlbum> = [];

  constructor(private app: AppService, private http: HttpClient,
   private uploadService: UploadFileService, private _lightbox: Lightbox) {
    this.uploadService.getFiles().subscribe((images: Object[]) => {
        console.log(images);
        for(let image of images) {
          const album = {
            src: image.path,
            caption: null,
            thumb: null
          };
          console.log(album);
          this._albums.push(album);
        }
    });
  }

  authenticated() { return this.app.authenticated; }

  open(index: number) {
    console.log("Hallo ", index);
    this._lightbox.open(this._albums, index);
  }


  ngOnInit() {

  }
}
