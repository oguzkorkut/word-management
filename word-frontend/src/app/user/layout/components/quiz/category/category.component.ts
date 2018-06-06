import { state } from '@angular/animations';
import { NotificationsService } from 'angular2-notifications';
import { Component, OnInit } from '@angular/core';

import { NgForm} from '@angular/forms';

import { Router, ActivatedRoute } from '@angular/router';
import { CategoryService } from '../category.service';
import { ReturnModel } from '../../../../../model/ReturnModel';
import { Category } from '../../../../../model/Category';


@Component({ 
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  providers: [CategoryService]

})
export class CategoryComponent implements OnInit {

  public loading = false;
  title: string = "Quiz";

  constructor(private categoryService: CategoryService,
              private notificationsService: NotificationsService,
              private router: Router, private route: ActivatedRoute) {}

  returnModel: ReturnModel;
   
  model: Category= new Category();

  ngOnInit() {
   this.route.params.subscribe(params => {
      this.loading = true;

      this.loading = false;
    });
  }

  checkout(form: NgForm) {
      if(form.invalid) {
        return;
      } else {
        this.categoryService.saveCategory(this.model).subscribe(
          response  => {
            console.log(response);

            if(response.status){
              this.notificationsService.info('Successfull','Category is saved!');
              this.model.category = "";
              form.reset;
            } else {
              this.notificationsService.warn('Warning',response.message);
            }
            //this.router.navigate(["/user/category-operations/categories"]);
            //window.location.reload();
        },
        error =>{
          console.log(error);
        });
      }
    }

  addToCategory(){
    
  }

}
