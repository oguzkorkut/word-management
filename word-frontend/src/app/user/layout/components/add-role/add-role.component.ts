import { state } from '@angular/animations';
import { UserService } from './../../../../service/user.service';
import { Role } from './../../../../model/role';
import { NotificationsService } from 'angular2-notifications';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppPager } from '../../../../AppPager';

@Component({ 
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {
  public loading = false;

  filterText: string;

  pager: AppPager = new AppPager();

  roles: Role[] = [];

  constructor( private route: ActivatedRoute, private notificationsService: NotificationsService, private userService: UserService) { }

  ngOnInit() {
    console.log("add-role");
    this.route.params.subscribe(params => {
 
      this.getRoles();
      
    });
  }

  add(inputText: HTMLInputElement){
    if(inputText.value){
      var role = new Role;

      role.name = inputText.value;

      this.loading = true;

      this.userService.addRole(role).subscribe( response  => {
        console.log(response );
        if(response.status){
          if(response.result){
            this.roles = response.result as Role[];
            this.pager = this.getPager(this.roles.length,1,5);
          }
          
          this.getRoles();
        
       	  this.notificationsService.success('Successfull',response.message);
        
          inputText.value = "";
        } else {
          this.notificationsService.warn('Error',response.message)
        }     
        this.loading = false;
      },
      error =>{
        this.loading = false;
        console.log(error);
        this.notificationsService.error('Error',error);
      });
    } else {
      this.notificationsService.warn('Warning','Boş kayıt eklenemez.');
    }
  }
 
  delete(id: number){
    this.loading = true;

    this.userService.deleteRole(id).subscribe(
      response  => {
        console.log(response )
        if(response.status){
          this.notificationsService.success('Successfull','Role is deleted!');
          this.getRoles();
        } else{
          this.notificationsService.error('Error',response.message);
        }
        this.loading = false;
    },
    error =>{
      this.loading = false;
      console.log(error )
      this.notificationsService.error('Error',error);
    });
  }

  getRoles(){
    this.loading = true;

    this.userService.getRoles().subscribe( response  => {
      console.log(response );
      if(response.status){
        if(response.result){
          this.roles = response.result as Role[];
          this.pager = this.getPager(this.roles.length,1,5);
        } 
      } else{
        this.notificationsService.error('Error',response.message);
      }
       
      this.loading = false;
    },
    error =>{
      this.loading = false;
      console.log(error);
      this.notificationsService.error('Error',error);
    });
  }

  getPager(totalItems: number, currentPage: number, pageSize: number=10): AppPager{
    let totalPage = Math.ceil(totalItems/pageSize);

    let pages: Array<number>= [];
    for(let i=0 ; i<totalPage; i++){
      pages.push(i+1);
    }

    var pager = new AppPager();

    pager.pageSize= pageSize;
    pager.currentPage = currentPage;
    pager.pageList = pages;

    return pager;
  }

  setPage(page:number){
    this.pager.currentPage= page;
  }
}
