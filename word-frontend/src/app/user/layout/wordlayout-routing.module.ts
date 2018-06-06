import { WordComponent } from './components/word/word/word.component';
import { CategoryComponent } from './components/quiz/category/category.component';
import { AddRoleComponent } from './../../user/layout/components/add-role/add-role.component';
import { AddUserComponent } from './../../user/layout/components/add-user/add-user.component';
import { RoleMappingComponent } from './../../user/layout/components/role-mapping/role-mapping.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WordLayoutComponent } from './wordlayout.component';
import { UserLogoutComponent } from '../user-logout/user-logout.component';
import { CommonModule } from '@angular/common';

import { AuthGuard } from '../../service/auth-guard.service';
import { CategoryDetailComponent } from './components/quiz/category-detail/category-detail.component';
import { CategoryQuestionDetailComponent } from './components/quiz/category-question-detail/category-question-detail.component';
import { LevelComponent } from './components/word/level/level.component';
import { LevelMappingComponent } from './components/word/level/level-mapping/level-mapping.component';
//import { CategoryDetailComponent } from './components/quiz/category-detail/category-detail.component';
//import { CategoryQuestionDetailComponent } from './components/quiz/category-question-detail/category-question-detail.component';


const routes: Routes = [
  {
    path: '', 
    component: WordLayoutComponent,
    children: [
      { path: '', redirectTo: 'user' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'user-operations/add-user/:operationName', component: AddUserComponent },
      { path: 'user-operations/role-mapping/:operationName', component: RoleMappingComponent },
      { path: 'role-operations/add-role/:operationName', component: AddRoleComponent },
      { path: 'category-operations/categories', component: CategoryDetailComponent },   
      { path: 'category-operations/categories/add-category', component: CategoryComponent },
      { path: 'category-operations/categories/category-detail', component: CategoryQuestionDetailComponent },
      { path: 'category-operations/categories/category-detail/:id', component: CategoryQuestionDetailComponent },

      { path: 'word/level-operations/all-levels', component: LevelComponent},
      { path: 'word/level-operations/level-mapping', component: LevelMappingComponent},

      { path: 'word/word-operations/all-words', component: WordComponent},

      { path: 'logout', component: UserLogoutComponent }
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [CommonModule,RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WordLayoutRoutingModule { }
