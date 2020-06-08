import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ServiceBase} from '../common/service-base';
import {DeviceTemp} from '../interfaces/device-temp';

@Injectable({
  providedIn: 'root'
})
export class DeviceTempService {

  constructor(private http: HttpClient) {
  }

  public convertToDecimalAndSave(value: string) {
    return this.http.get(`${environment.apiUrl}/devicetemp/${value}`, {headers: ServiceBase.getHeaders()});
  }

  public getDevicesWithLatestTemp() {
    return this.http.get<DeviceTemp[]>(`${environment.apiUrl}/devicetemp/latest`, {headers: ServiceBase.getHeaders()});
  }

  public getAllTempByDeviceId(deviceId: string) {
    return this.http.get<DeviceTemp[]>(`${environment.apiUrl}/devicetemp/device/${deviceId}`, {headers: ServiceBase.getHeaders()});
  }
}
