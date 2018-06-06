import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  operationName: string = "";
  public loading = false;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("add-user");
    this.route.params.subscribe(params => {
      this.loading = true;
      this.operationName = params.operationName;
      console.log(this.operationName);

      this.loading = false;
      
    });
  }

}
