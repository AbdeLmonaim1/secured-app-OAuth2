import {Component, OnInit, signal} from '@angular/core';
import {Security} from './services/security';
import {KeycloakService} from 'keycloak-angular';
import {KeycloakProfile} from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App implements OnInit{
  profile?:KeycloakProfile;
  protected readonly title = signal('customer-front-angular-app');
  constructor(public keycloak: KeycloakService) {
  }
  ngOnInit() {
    if (this.keycloak.isLoggedIn()){
      this.keycloak.loadUserProfile().then(profile =>{
        this.profile = profile;
        console.log("The user authenticated => ", this.profile);
      })
    }
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
