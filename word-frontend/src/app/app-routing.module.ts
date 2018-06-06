import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './user/user-login/user-login.component';
import { AuthGuard } from './service/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: 'userlogin' , pathMatch: 'full' },
  { path: 'userlogin', loadChildren: './user/user-login/user-login.module#UserLoginModule' },
  { path: 'logout', loadChildren: './user/user-logout/user-logout.module#UserLogoutModule' },
  { path: 'user' , loadChildren: './user/layout/wordlayout.module#WordLayoutModule', canActivate: [AuthGuard]},
  //{ path: 'admin', loadChildren: './admin/layout/layout.module#LayoutModule', canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
