import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({ 
  selector: 'app-add-word',
  templateUrl: './add-word.component.html',
  styleUrls: ['./add-word.component.css']
})
export class AddWordComponent implements OnInit {

  public loading = false;

  constructor( private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("add-role");
    this.route.params.subscribe(params => {
      this.loading = true;

      this.loading = false;
      
    });
  }
}
