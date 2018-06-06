import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-role-mapping',
  templateUrl: './role-mapping.component.html',
  styleUrls: ['./role-mapping.component.css']
})
export class RoleMappingComponent implements OnInit {

  operationName: string = "";
  public loading = false;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("role-mappign");
    this.route.params.subscribe(params => {
      this.loading = true;
      this.operationName = params.operationName;
      console.log(this.operationName);

      this.loading = false;
      
    });
  }

}
