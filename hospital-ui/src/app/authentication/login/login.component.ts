import { AuthService } from './../../service/auth.service';
import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger('gradientAnimation', [
      state('default', style({ background: 'linear-gradient(to top, #30cfd0 0%, #330867 100%)' })),
      state('hovered', style({ background: 'linear-gradient(to right top, #30cfd0 0%, #330867 100%)' })),
      transition('default <=> hovered', animate('400ms ease-out'))
    ])
  ]
})
export class LoginComponent {
	@ViewChild('f', {static: false})
	loginForm: NgForm;

	animationState: string = 'default';

	constructor(private authService: AuthService, private router: Router) {}

	onSubmit(loginForm: NgForm) {}

}
