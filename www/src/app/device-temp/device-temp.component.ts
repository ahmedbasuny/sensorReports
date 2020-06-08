import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {DeviceTempService} from '../shared/services/device-temp.service';
import {MatTableDataSource} from '@angular/material/table';
import {DeviceTemp} from '../shared/interfaces/device-temp';
import {MatPaginator} from '@angular/material/paginator';
import {environment} from '../../environments/environment';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

@Component({
  selector: 'app-device-temp',
  templateUrl: './device-temp.component.html',
  styleUrls: ['./device-temp.component.css']
})
export class DeviceTempComponent implements OnInit {

  value: string = '';
  devicesData: DeviceTemp[] = [];
  displayedColumns: string[] = ['deviceId', 'temp'];
  dataSource;
  deviceDataSource;
  stompClient;

  @ViewChild('mainPaginator', {static: true}) paginator: MatPaginator;
  @ViewChild('devicePaginator', {static: true}) devicePaginator: MatPaginator;

  constructor(private deviceTempService: DeviceTempService, private changeDetectorRefs: ChangeDetectorRef) {
    this.initializeWebSocketConnection();
  }

  ngOnInit(): void {
    this.deviceTempService.getDevicesWithLatestTemp().subscribe(res => {
      console.log('ngOnInit | res', res);
      this.dataSource = new MatTableDataSource<DeviceTemp>(res);
      this.dataSource.paginator = this.paginator;
    }, error => {
      console.log('ngOnInit | error', error);
    });
  }

  saveValues() {
    this.deviceTempService.convertToDecimalAndSave(this.value).subscribe(res => {
      console.log('saveValues | res', res);
    });
    this.value = '';
  }

  getDeviceData(row: any) {
    console.log('getDeviceData | row', row);
    this.deviceTempService.getAllTempByDeviceId(row.deviceId).subscribe(res => {
      console.log('getDeviceData | res', res);
      this.deviceDataSource = new MatTableDataSource<DeviceTemp>(res);
      this.deviceDataSource.paginator = this.devicePaginator;
      this.changeDetectorRefs.detectChanges();
    }, error => {
      console.log('getDeviceData | error', error);
    });
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(`${environment.socketServerUrl}`);
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;
    this.stompClient.reconnect_delay = 5000;
    const that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/message', (message) => {
        console.log('message ', message);
        if (message.body) {
          console.log('message body ', message.body);
          that.refresh();
        }
      });
    });
  }

  refresh() {
    this.deviceTempService.getDevicesWithLatestTemp().subscribe(res => {
      console.log('refresh | res', res);
      this.dataSource = new MatTableDataSource<DeviceTemp>(res);
      this.dataSource.paginator = this.paginator;
      this.changeDetectorRefs.detectChanges();
    }, error => {
      console.log('ngOnInit | error', error);
    });
  }
}
