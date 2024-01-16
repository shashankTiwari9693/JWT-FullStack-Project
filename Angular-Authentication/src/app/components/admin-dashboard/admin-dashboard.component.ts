import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent {
  message: String;

  constructor(
    private service: AuthService
  ) { }

  ngOnInit() {
    this.Adminhello();
  }

 Adminhello() {
    this.service.Adminhello().subscribe((response) => {
      console.log(response);
      this.message = response.message;
    })
  }
}
