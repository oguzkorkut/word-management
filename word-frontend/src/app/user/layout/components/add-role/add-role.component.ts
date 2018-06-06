import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({ 
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {

 
  operationName: string = "";
  public loading = false;

  constructor( private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("add-role");
    this.route.params.subscribe(params => {
      this.loading = true;
      this.operationName = params.operationName;
      console.log(this.operationName);

      this.loading = false;
      
    });
  }

}
