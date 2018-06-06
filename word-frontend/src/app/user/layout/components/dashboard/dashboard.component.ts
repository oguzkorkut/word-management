import { WordLayoutComponent } from './../../wordlayout.component';
import { Component, NgModule, OnInit } from '@angular/core';
import { NgxChartsModule } from '@swimlane/ngx-charts';
 
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  operationName: string = "";
  public loading = false;

  constructor(private wordLayoutComponent: WordLayoutComponent) {
  }

  ngOnInit() {
    this.loading = true;
    this.wordLayoutComponent.operationName = "Dashboard";
    this.loading = false;
  }

  
  onSelect(event) {
    console.log(event);
  }
 


}

