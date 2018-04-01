import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UploadFileService {

constructor(private http: HttpClient) {}

  pushFileToStorage(file: File, tags: String): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('name',file.name);
    formdata.append('tags',JSON.stringify(tags.split(" ")));

    const req = new HttpRequest('POST', '/api/post', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<string[]> {
    return this.http.get<string[]>('/api/get')
  }
}
