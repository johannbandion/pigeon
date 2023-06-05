import { TestBed } from '@angular/core/testing';

import { AddfriendService } from './addfriend.service';

describe('AddFriendService', () => {
  let service: AddfriendService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddfriendService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
