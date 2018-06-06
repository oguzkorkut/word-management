import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { Category } from '../../../../../model/Category';
import { ReturnModel } from '../../../../../model/ReturnModel';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {

 
  public loading = false;

  title: string = "Quiz";

  constructor(private categoryService: CategoryService, private route: ActivatedRoute) {}

  selectedCategory: Category;

  returnModel: ReturnModel;

  categories: Category[] = [];
  
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.loading = true;

      this.loading = false;
    });
    this.getCategories();
  }

  getCategories() {
    //this.categoryService.getCategories().subscribe(c => (this.returnModel = c));
    this.categoryService.getCategories().subscribe(
      response  => {
        console.log(response )
        this.categories = response.result as Category[];
      },
      error =>{
        console.log(error )
      });
  }

  onSelect(category?: Category) {
    if (category) {
      this.selectedCategory = category;
    } else {
      this.selectedCategory = null;
    }
  }

  addToCategory(){
    
  }

}
