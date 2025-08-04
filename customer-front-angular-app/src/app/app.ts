import {Component, OnInit, signal} from '@angular/core';
import {Security} from './services/security';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App implements OnInit{
  protected readonly title = signal('customer-front-angular-app');
  constructor(public securityService: Security, private keycloak: KeycloakService) {
  }
  ngOnInit() {
  }

  logout() {
    this.keycloak.logout();
  }

 async login() {
    await this.keycloak.login({
      redirectUri: window.location.origin
    });
  }
}
