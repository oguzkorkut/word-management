import { LevelFilterPipe } from './components/word/level-filter.pipe';
import { CategoryComponent } from './components/quiz/category/category.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WordLayoutComponent } from './wordlayout.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { MatTabsModule, MatListModule, MatIconModule, MatToolbarModule, MatAutocompleteModule, MatButtonToggleModule, MatDialogModule, MatChipsModule, MatCardModule, MatButtonModule, MatCheckboxModule, MatDividerModule, MatGridListModule, MatDatepickerModule, MatInputModule, MatMenuModule, MatNativeDateModule, MatProgressBarModule, MatProgressSpinnerModule, MatPaginatorModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatStepperModule, MatTableModule, MatTooltipModule, MatSortModule } from '@angular/material';
import { BannerComponent } from './components/banner/banner.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { WordLayoutRoutingModule } from './wordlayout-routing.module';
import { UserLogoutComponent } from '../user-logout/user-logout.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatExpansionModule } from '@angular/material/expansion';
import { LoadingModule } from 'ngx-loading';
import { LayoutModule } from '@angular/cdk/layout';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AddRoleComponent } from './components/add-role/add-role.component';
import { RoleMappingComponent } from './components/role-mapping/role-mapping.component';
import { CategoryDetailComponent } from './components/quiz/category-detail/category-detail.component';
import { CategoryQuestionDetailComponent } from './components/quiz/category-question-detail/category-question-detail.component';
import { QuestionFilterPipe } from './components/quiz/category-question-detail/question-filter.pipe';
import { AnswerDetailDialogPanel } from './components/quiz/category-question-detail/answer-dialog/answer-detail-dialog-panel';
import { QuestionDetailDialogPanel } from './components/quiz/category-question-detail/question-dialog/question-detail-dialog-panel';
import { AddLevelComponent } from './components/word/level/add-level/add-level.component';
import { AddWordComponent } from './components/word/word/add-word/add-word.component';
import { LevelComponent } from './components/word/level/level.component';
import { WordComponent } from './components/word/word/word.component';
import { LevelMappingComponent } from './components/word/level/level-mapping/level-mapping.component';
import { WordService } from '../../service/word.service';
//import { CategoryQuestionDetailComponent } from './components/quiz/category-question-detail/category-question-detail.component';

@NgModule({
  imports: [
    CommonModule, FormsModule, WordLayoutRoutingModule, MatTabsModule, NgxChartsModule , MatTabsModule, MatSidenavModule, MatListModule,
    NgxChartsModule, LoadingModule, LayoutModule, MatIconModule, MatToolbarModule, MatExpansionModule, MatExpansionModule,
    ReactiveFormsModule, MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule,
    MatDatepickerModule, MatDialogModule, MatDividerModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule,
    MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatRadioModule, MatRippleModule,
    MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatStepperModule,
    MatTableModule, MatTabsModule, MatToolbarModule, MatTooltipModule
  ],
  declarations: [
    DashboardComponent,
    WordLayoutComponent,
    BannerComponent,
    FooterComponent,
    UserLogoutComponent,
    AddUserComponent,
    AddRoleComponent,
    RoleMappingComponent,
    CategoryDetailComponent,
    CategoryComponent,
    CategoryQuestionDetailComponent,
    QuestionFilterPipe,
    AnswerDetailDialogPanel,
    QuestionDetailDialogPanel,
    AddLevelComponent,
    AddWordComponent,
    LevelComponent,
    WordComponent,
    LevelMappingComponent,
    LevelFilterPipe
  ],
  providers: [
    WordService
  ],
  bootstrap: [],
  entryComponents: [
    AnswerDetailDialogPanel,
    QuestionDetailDialogPanel
  ]
})
export class WordLayoutModule { }