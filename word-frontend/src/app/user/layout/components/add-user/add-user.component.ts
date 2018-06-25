import { UserFormDialogComponent } from './user-form-dialog/user-form-dialog.component';
import { User } from './../../../../model/user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppPager } from '../../../../AppPager';
import { NotificationsService } from 'angular2-notifications';
import { UserService } from '../../../../service/user.service';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  public loading = false;

  filterText: string;

  pager: AppPager = new AppPager();

  users: User[] = [];

  constructor(private route: ActivatedRoute, private notificationsService: NotificationsService, 
    private userService: UserService, public dialog: MatDialog) { }

  ngOnInit() {
    console.log("add-user");
    this.loading = true;
    this.route.params.subscribe(params => {
      this.getAllUsers();
    });

    this.loading = false;
  }

  openUserDialog(): void{
    let dialogRef = this.dialog.open(UserFormDialogComponent, {
      width: '400px',
      data: { }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result){
        this.add(result);
      }
    });
  }

  add(inputText: HTMLInputElement){
    if(inputText.value){
      var user = new User;

      user.username = inputText.value;

      this.loading = true;
/*
      this.userService.addRole(user).subscribe( response  => {
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
      });*/
    } else {
      this.notificationsService.warn('Warning','Boş kayıt eklenemez.');
    }
  }


  delete(id: number){
    this.loading = true;

    this.userService.deleteUser(id).subscribe(
      response  => {
        console.log(response )
        if(response.status){
          this.notificationsService.success('Successfull','Role is deleted!');
          this.getAllUsers();
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

  getAllUsers(){
    this.loading = true;

    this.userService.getAllUsers().subscribe( response  => {
      console.log(response );
      if(response.status){
        if(response.result){
          this.users = response.result as User[];
          this.pager = this.getPager(this.users.length,1,5);
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
