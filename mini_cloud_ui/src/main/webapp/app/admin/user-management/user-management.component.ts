import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { EventManager, PaginationUtil, ParseLinks, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal, User, UserService } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-user-mgmt',
    templateUrl: './user-management.component.html'
})
export class UserMgmtComponent implements OnInit, OnDestroy {

    currentAccount: any;
    users: User[];
    error: any;
    success: any;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private userService: UserService,
        private parseLinks: ParseLinks,
        private alertService: AlertService,
        private principal: Principal,
        private eventManager: EventManager,        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {
        this.jhiLanguageService.setLocations(['user-management']);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.loadAll();
            this.registerChangeInUsers();
        });
    }

    ngOnDestroy() {
    }

    registerChangeInUsers() {
        this.eventManager.subscribe('userListModification', (response) => this.loadAll());
    }

    setActive (user, isActivated) {
        user.activated = isActivated;

        this.userService.update(user).subscribe(
            response => {
                if (response.status === 200) {
                    this.error = null;
                    this.success = 'OK';
                    this.loadAll();
                } else {
                    this.success = null;
                    this.error = 'ERROR';
                }
            });
    }

    loadAll () {
        this.userService.query().subscribe(
            (res: Response) => this.onSuccess(res.json(), res.headers),
            (res: Response) => this.onError(res.json())
        );
    }

    trackIdentity (index, item: User) {
        return item.id;
    }

    private onSuccess(data, headers) {
        // hide anonymous user from user management: it's a required user for Spring Security
        let hiddenUsersSize = 0;
        for (let i in data) {
            if (data[i]['login'] === 'anonymoususer') {
                data.splice(i, 1);
                hiddenUsersSize++;
            }
        }
        this.users = data;
    }

    private onError(error) {
        this.alertService.error(error.error, error.message, null);
    }
}
