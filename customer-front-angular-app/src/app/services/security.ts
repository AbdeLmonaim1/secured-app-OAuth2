import {Injectable} from '@angular/core';
import {KeycloakEventTypeLegacy, KeycloakService} from 'keycloak-angular';
import {KeycloakProfile} from "keycloak-js";

@Injectable({
  providedIn: 'root'
})
export class Security {
  public profile?: KeycloakProfile;
  constructor(private kcService: KeycloakService) {
    this.init();
  }
  init() {
    this.kcService.keycloakEvents$.subscribe({
      next: e =>{
        if(e.type == KeycloakEventTypeLegacy.OnAuthSuccess){
          //When authentication is success we can get the profile
          this.kcService.loadUserProfile().then(profile =>{
            this.profile = profile;
            console.log("The user authenticated => ", this.profile);
          })
        }
      }
    })
  }
  public hasRoleIn(roles: string[]): boolean {
    let userRoles = this.kcService.getUserRoles();
    for (let role of userRoles) {
      if(userRoles.includes(role)) return true
      } return false;
  }

}
