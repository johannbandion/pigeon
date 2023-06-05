import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AddFriendService {

  constructor(private http: HttpClient) { }

  getFriends(search: string, page: number, pageSize: number){
    return this.http.get<UserEntity[]>(
      environment.apiPath + '/friends/getUsers?search=' + search + '&page=' + page + '&size=' + pageSize,
      {observe: 'response'}
    )
  }
}