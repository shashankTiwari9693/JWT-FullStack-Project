import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    });
  }

  login() {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe((response) => {
      console.log('shashank', response);
      if (response.token) {
        alert(response.token);
        const jwtToken = response.token;
        localStorage.setItem('JWT', jwtToken);
        if (this.loginForm.value.email == 'admin@gmail.com') {
          this.router.navigateByUrl('/adminDashboard');
        } else {
          this.router.navigateByUrl('/dashboard');
        }
      }
    });
  }
}
