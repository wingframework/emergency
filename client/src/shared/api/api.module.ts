import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { DefaultService } from './api/default.service';
import { BasicErrorControllerService } from './api/basicErrorController.service';
import { IndexControllerService } from './api/indexController.service';
import { MenuControllerService } from './api/menuController.service';
import { PassportControllerService } from './api/passportController.service';
import { RoleControllerService } from './api/roleController.service';
import { SchedulControllerService } from './api/schedulController.service';
import { SmsControllerService } from './api/smsController.service';
import { StaffControllerService } from './api/staffController.service';
import { UserControllerService } from './api/userController.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    DefaultService,
    BasicErrorControllerService,
    IndexControllerService,
    MenuControllerService,
    PassportControllerService,
    RoleControllerService,
    SchedulControllerService,
    SmsControllerService,
    StaffControllerService,
    UserControllerService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
