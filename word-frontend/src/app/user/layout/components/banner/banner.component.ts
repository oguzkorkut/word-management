import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  activeTab = 'dashboard';
  constructor() { }

  ngOnInit() {
    if (location.pathname.slice(1) === '') {
      this.setActive(this.activeTab);
    } else {
      this.setActive(location.pathname.slice(1));
    }
  }
  setActive(activeName: string) {
    this.activeTab = activeName;
  }

}
