import { ChangeDetectionStrategy, Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { DA_SERVICE_TOKEN, ITokenService } from '@delon/auth';
import { SettingsService, User } from '@delon/theme';
import { PassportControllerService, UserControllerService } from 'src/shared/api';

@Component({
  selector: 'header-user',
  template: `
    <div class="alain-default__nav-item d-flex align-items-center px-sm" nz-dropdown nzPlacement="bottomRight" [nzDropdownMenu]="userMenu">
      <nz-avatar [nzSrc]="user.avatar || '/assets/tmp/img/avatar.jpg'" nzSize="small" class="mr-sm"></nz-avatar>
      {{ user.username }}
    </div>
    <nz-dropdown-menu #userMenu="nzDropdownMenu">
      <div nz-menu class="width-sm">
        <div nz-menu-item routerLink="/pro/account/center">
          <i nz-icon nzType="user" class="mr-sm"></i>
          {{ 'menu.account.center' | i18n }}
        </div>
        <div nz-menu-item routerLink="/pro/account/settings">
          <i nz-icon nzType="setting" class="mr-sm"></i>
          {{ 'menu.account.settings' | i18n }}
        </div>
        <div nz-menu-item routerLink="/exception/trigger">
          <i nz-icon nzType="close-circle" class="mr-sm"></i>
          {{ 'menu.account.trigger' | i18n }}
        </div>
        <li nz-menu-divider></li>
        <div nz-menu-item (click)="logout()">
          <i nz-icon nzType="logout" class="mr-sm"></i>
          {{ 'menu.account.logout' | i18n }}
        </div>
      </div>
    </nz-dropdown-menu>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderUserComponent {
  // get user(): User {
  //   return this.settings.user;
  // }
  user: User = {};
  async ngOnInit() {
    let rtn = await this.passport.getUserInfoUsingGET().toPromise();
    if (rtn.ok) {
      this.user = rtn.data as User;
    }
  }

  constructor(
    private settings: SettingsService,
    private router: Router,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    private userControllerService: UserControllerService,
    private passport: PassportControllerService
  ) {}

  logout(): void {
    this.tokenService.clear();
    this.router.navigateByUrl(this.tokenService.login_url!);
  }
}
