import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
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
  users?: String[];
  dataSource: MatTableDataSource<String> | undefined;

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  displayedColumns: string[] = ['user', "add"];

  constructor(
    private addfriendService: AddfriendService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit(): void{
    this.addfriendService.getFriends('', this.page, this.pageSize).subscribe(response => {
      this.length = Number(response.headers.get('X-Total-Count')) || 0;
      this.users = response.body as String[];
      this.dataSource = new MatTableDataSource<String>(this.users);
    })
  }

  addFriend(userName: string){
    console.log(userName);
    this.addfriendService.addFriend(userName).subscribe({
      next: (response) => {
        console.log(response.status);
        if (response.status == 200) {
          this.snackBar.open(
            "Friend added!",
            "Dismiss",
            {
              duration: 2000,
              panelClass: ['.snackbar_ok']
            }
          );
        }
      },
      error: (err) => {
        this.snackBar
          .open(
            err.error || "Failed to add friend!",
            "Dismiss",
            {
              duration: 2000,
              panelClass: ['.snackbar_error']
            });
      }
    })
  }

  handlePageEvent(event: PageEvent) {
    this.page = event.pageIndex + 1;
    this.pageSize = event.pageSize;

    this.addfriendService.getFriends('', event.pageIndex + 1, event.pageSize).subscribe(response => {
      this.length = Number(response.headers.get('X-Total-Count')) || 0;
      this.users = response.body as String[];
      this.dataSource = new MatTableDataSource<String>(this.users);
    });
  }

  searched(event: Event) {
    this.page = 1;
    this.addfriendService
      .getFriends(this.searchString, this.page, this.pageSize)
      .subscribe(
        {
          next: (response => {
            if (this.paginator) {
              this.paginator.pageIndex = 0;
            }
            this.length = Number(response.headers.get('X-Total-Count')) || 0;
            this.users = response.body as String[];
            this.dataSource = new MatTableDataSource<String>(this.users);
          }),
          error: (response => {
            this.length = Number(response.headers.get('X-Total-Count')) || 0;
            this.users = [];
            this.dataSource = new MatTableDataSource<String>(this.users);
          })
        });
  }

  onUserDetailPopUp(user: UserEntity){
    console.log(user);
  }
}


