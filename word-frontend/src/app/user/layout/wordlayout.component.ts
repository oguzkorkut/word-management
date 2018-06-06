import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatSidenav } from '@angular/material';
import { MediaMatcher } from '@angular/cdk/layout';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { CookieService } from 'ngx-cookie';
import { SubMenu } from './sub-menu';
import { Menu } from './menu';
 
@Component({  
  selector: 'app-layout',
  templateUrl: './wordlayout.component.html',
  styleUrls: ['./wordlayout.component.css']
})
export class WordLayoutComponent implements OnInit {

  @ViewChild('snav') sidenav: MatSidenav;

  menuList: Menu[];

  quizList: Menu[];

  wordList: Menu[];
   
  tabName: String = '';
  username: String;
  ePosta: String;

  public loading = false;
  activeTab = 'dashboard';
  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;
  operationName: string = "";

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, 
    private route: ActivatedRoute, private router: Router, 
    private _cookieService: CookieService) {
      this.mobileQuery = media.matchMedia('(max-width: 600px)');
      this._mobileQueryListener = () => changeDetectorRef.detectChanges();
      this.mobileQuery.addListener(this._mobileQueryListener);
  }
  openLink(operationName=''){
    this.operationName = operationName;
  }
  // tslint:disable-next-line:use-life-cycle-interface
  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
  ngOnInit() {

    this.createMenu();
    
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
          return;
      }
      window.scrollTo(0, 0);
    });

    this.init();
  }
  async init() {
    this.loading = true;
    await this.getFunction();
    this.username = "sa";
    this.route.firstChild.params.subscribe(params => {
      this.operationName = params.operationName;
    });
    this.loading = false;
  }
 
  toggle() {
    
  }
  close() {
    this.sidenav.close();
  }
  private async getFunction() {
    try {
      
    } catch (error) {
      this.loading = false;
      console.log('Hata!!: ' + error);
    }
  }

  createMenu(){

      const userSubMenuList: SubMenu[] = [
        {id:1,name:"Kullanıcı Ekle", shortName:"add-user", summary:"Kullanici Ekleme" },
        {id:2,name:"Kullanıc-Rol Tanımlama", shortName:"role-mapping", summary:"Kullanıcı/Rol Tanımlama" }
      ];
  
      const roleSubMenuList: SubMenu[] = [
        {id:1,name:"Rol Ekle", shortName:"add-role", summary:"Rol Ekleme" }
      ];
      
      this.menuList = [
        {id:1,name:"Kullanıcı İşelmeleri", shortName:"user-operations", summary:"Kullanıcı İşelmeleri",subMenu:userSubMenuList },
        {id:2,name:"Rol İşelmeleri", shortName:"role-operations", summary:"Rol İşelmeleri",subMenu:roleSubMenuList }
      ];
  
  
      const quizSubMenuList: SubMenu[] = [
        {id:1,name:"Kategoriler", shortName:"categories", summary:"Kategoriler" }
      ];
      
      this.quizList = [
        {id:1,name:"Kategori", shortName:"category-operations", summary:"Kategori İşelmeleri",subMenu:quizSubMenuList }
      ];

      const wordSubMenuList: SubMenu[] = [
        {id:1,name:"Kelimeler", shortName:"all-words", summary:"Kelimeler"}
      ];

      const levelSubMenuList: SubMenu[] = [
        {id:1,name:"Seviyeler", shortName:"all-levels", summary:"Seviyeler"},
        {id:2,name:"Seviye-Kelime Tanımlama", shortName:"level-mapping", summary:"Seviye-Kelime Tanımlama"}
      ];
      
      this.wordList = [
        {id:1,name:"Kelime İşlemleri", shortName:"word/word-operations", summary:"Kelime İşelmeleri",subMenu:wordSubMenuList },
        {id:2,name:"Seviye İşlemleri", shortName:"word/level-operations", summary:"Seviye İşelmeleri",subMenu:levelSubMenuList }
      ];
    }

}

