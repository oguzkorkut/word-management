import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-level-mapping',
  templateUrl: './level-mapping.component.html',
  styleUrls: ['./level-mapping.component.css']
})
export class LevelMappingComponent implements OnInit {

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
