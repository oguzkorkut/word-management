import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-word',
  templateUrl: './word.component.html',
  styleUrls: ['./word.component.css']
})
export class WordComponent implements OnInit {

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
