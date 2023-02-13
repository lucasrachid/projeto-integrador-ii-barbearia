import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";
import Constants from '../../components/constants';
import snackBarSuccessConfig from '../../components/snackBarSuccessConfig';
import snackBarErrorConfig from '../../components/snackBarErrorConfig';
import { Permissions } from '../../models/permissions';
import User from '../../models/User';
import { AuthService } from '../../services/authService';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = this.formBuilder.group({
    username: '',
    password: ''
  });

  logoString: string = Constants.LOGIN_LOGO;

  permissions: Permissions = new Permissions();

  loadingResponse = false;
  revealedPassword: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private _snackBar: MatSnackBar,
              private router: Router,
              private translateService: TranslateService,
              private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  login() {
    this.loadingResponse = true;

    const {username, password} = this.loginForm.value;
    let user: User = new User();
    user.username = username!;
    user.password = password!;

    let validation = this.validateLoginAndPassword(user);

    if (validation === true) {
      this.authService.login(user).subscribe(response => {
        let token = response.object.jwt.access_token;

        let roles: any[] = response.object.roles;

        for (let i = 0; i < roles.length; i++) {

          if (roles[i].role === Constants.BARBEARIA_ROLE_ADMIN) {
            this.permissions.BARBEARIA_ROLE_ADMIN = "BARBEARIA_ROLE_ADMIN";
          }

          if (roles[i].role === Constants.BARBEARIA_ROLE_OBJECT_STORAGE) {
            this.permissions.BARBEARIA_ROLE_OBJECT_STORAGE = "BARBEARIA_ROLE_OBJECT_STORAGE";
          }

        }

        let objectToLocalStorage = {token: token, roles: this.permissions};
        this.authService.setInfoUserLocalStorage(JSON.stringify(objectToLocalStorage));

        let userAuthentication = this.authService.getUserByToken();

        this.translateService.get(response.message).subscribe((translatedOrNot: string) => {
          this._snackBar.open(translatedOrNot, "✖", snackBarSuccessConfig());
        });

        this.router.navigateByUrl('/home', {state: {token: userAuthentication}});
      }, () => {
      }, () => this.loadingResponse = false);
    }
  }

  revealPassword(){
    let inputPassword = document.getElementById('password');

    if (this.revealedPassword) {
      inputPassword!.setAttribute('type', 'password');
      this.revealedPassword = false;
    } else {
      inputPassword!.setAttribute('type', 'text');
      this.revealedPassword = true;
    }
  }

  validateLoginAndPassword(user: User) {

    if (user.username?.length === 0) {
      return this.translateService.get('MESSAGES.USERNAME_REQUIRED').subscribe((translatedOrNot: string) => {
        return this._snackBar.open(translatedOrNot, "✖", snackBarErrorConfig());
      });
    }

    if (user.password?.length === 0) {
      return this.translateService.get('MESSAGES.PASSWORD_REQUIRED').subscribe((translatedOrNot: string) => {
        return this._snackBar.open(translatedOrNot, "✖", snackBarErrorConfig());
      });
    }

    if (user.password?.length && user.password?.length <= 5) {
      return this.translateService.get('MESSAGES.PASSWORD_LENGHT_MIN').subscribe((translatedOrNot: string) => {
        return this._snackBar.open(translatedOrNot, "✖", snackBarErrorConfig());
      });
    }

    if (user.password?.length && user.password?.length > 100) {
      return this.translateService.get('MESSAGES.PASSWORD_LENGHT_MAX').subscribe((translatedOrNot: string) => {
        return this._snackBar.open(translatedOrNot, "✖", snackBarErrorConfig());
      });
    }

    return true;

  }

}
