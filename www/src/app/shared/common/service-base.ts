import {HttpHeaders} from '@angular/common/http';

export class ServiceBase {
  public static getHeaders() {
    const headers = new HttpHeaders()
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    return headers;
  }
}
