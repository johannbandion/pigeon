import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
// import {MatSnackBar} from "@angular/material/snack-bar";
import {AddfriendService} from "./addfriend.service";
import {MatDialog} from "@angular/material/dialog";
import {UserEntity} from "../../model/model";

@Component({
  selector: 'app-addfriend',
  templateUrl: './addfriend.component.html',
  styleUrls: ['./addfriend.component.scss']
})
export class AddfriendComponent implements OnInit{
  toggleSearch: boolean = false;

  searchString: string = '';

  length = 0;
  pageSize = 10;
  page = 1;
  users?: UserEntity[];
  dataSource: MatTableDataSource<UserEntity> | undefined;

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  displayedColumns: string[] = ['user', "add"];

  constructor(
    // private snackBar: MatSnackBar,
    private friendService: AddfriendService,
    private dialog: MatDialog) {
  }

  ngOnInit(): void{
    this.friendService.getFriends('', this.page, this.pageSize).subscribe(response => {
      this.length = Number(response.headers.get('X-Total-Count')) || 0;
      this.users = response.body as UserEntity[];
      this.dataSource = new MatTableDataSource<UserEntity>(this.users);
    })
  }

  openAddFriendPopup(){
    console.log("Add friend");
  }

  handlePageEvent(event: PageEvent) {
    this.page = event.pageIndex + 1;
    this.pageSize = event.pageSize;

    this.friendService.getFriends('', event.pageIndex + 1, event.pageSize).subscribe(response => {
      this.length = Number(response.headers.get('X-Total-Count')) || 0;
      this.users = response.body as UserEntity[];
      this.dataSource = new MatTableDataSource<UserEntity>(this.users);
    });
  }

  searched(event: Event) {
    this.page = 1;
    this.friendService
      .getFriends(this.searchString, this.page, this.pageSize)
      .subscribe(
        {
          next: (response => {
            if (this.paginator) {
              this.paginator.pageIndex = 0;
            }
            this.length = Number(response.headers.get('X-Total-Count')) || 0;
            this.users = response.body as UserEntity[];
            this.dataSource = new MatTableDataSource<UserEntity>(this.users);
          }),
          error: (response => {
            this.length = Number(response.headers.get('X-Total-Count')) || 0;
            this.users = [];
            this.dataSource = new MatTableDataSource<UserEntity>(this.users);
          })
        });
  }

  onUserDetailPopUp(user: UserEntity){
    console.log(user);
  }
}


