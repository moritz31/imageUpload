import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import { UploadFileService } from '../upload/upload-file.service';
import { Lightbox, LightboxConfig, LightboxEvent, LIGHTBOX_EVENT, IEvent, IAlbum } from 'angular2-lightbox';
import { Subscription } from 'rxjs/Subscription';
import {HttpResponse}from 'selenium-webdriver/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private _albums: Array<IAlbum> = [];

  constructor(private app: AppService, private http: HttpClient,
   private uploadService: UploadFileService, private _lightbox: Lightbox, private router: Router) {
    this.uploadService.getFiles().subscribe((images: any) => {

        for(let image of images) {
          if(image!=null) {
            const album = {
              src: image.path,
              caption: null,
              thumb: null,
              tags: JSON.parse(image.tags)
            };
            this._albums.push(album);
          }
        }
    }, error => this.router.navigate(['login']));
  }

  open(index: number) {
    this._lightbox.open(this._albums, index);
  }

  ngOnInit() {
    if(!this.app.isAuthenticated) {
      this.router.navigate(['login'])
    }
  }
}
