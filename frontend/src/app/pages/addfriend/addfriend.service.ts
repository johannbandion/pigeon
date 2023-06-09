import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {UserEntity} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class AddfriendService {

  constructor(private http: HttpClient) {
  }

  getFriends(search: string, page: number, pageSize: number) {
    return this.http.get<String[]>(
      environment.apiPath + '/addfriend/getUsers?search=' + search + '&page=' + page + '&size=' + pageSize,
      {observe: 'response'}
    )
  }

  addFriend(friend: string) {
    return this.http.post(
      environment.apiPath + '/addfriend',
      friend,
      {observe: 'response'}
    )
  }
}
