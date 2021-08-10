import { ErrorHandler } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserTodoComponent } from './user-todo/user-todo.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SignupComponent } from './signup/signup.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpIntercepterService } from './service/http/http-intercepter.service';
import { AppErrorHandler } from './error/app-error-handler';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { UpdateTodoComponent } from './update-todo/update-todo.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CreateTodoComponent } from './create-todo/create-todo.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MAT_MOMENT_DATE_FORMATS, MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import 'moment/locale/ca';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';

@NgModule({
  declarations: [//the following components are part of the specific module
    AppComponent,
    LoginComponent,
    HomeComponent,
    UserTodoComponent,
    ErrorpageComponent,
    HeaderComponent,
    FooterComponent,
    SignupComponent,
    AdminpageComponent,
    UpdateTodoComponent,
    CreateTodoComponent,

  ],
  imports: [//the components in this Module need the following Module dependencies to work
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatSlideToggleModule

  ],
  providers: [
    { provide: ErrorHandler, useClass: AppErrorHandler }, //whenever you are using errorhandle in the project, use AppErrorHandler instead
    { provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterService, multi: true },
    { provide: MAT_DATE_LOCALE, useValue: 'en_CA' },
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS },

  ],
  bootstrap: [AppComponent] //when this module loads, theses bootstrap components will be loaded at the same time 
})
export class AppModule { }
